# -*- mode: ruby -*-
# vi: set ft=ruby :

# Deserialize Ansible Galaxy installation metadata for a role
def galaxy_install_info(role_name)
  role_path = File.join("ansible", role_name)
  galaxy_install_info = File.join(role_path, "meta", ".galaxy_install_info")

  if (File.directory?(role_path) || File.symlink?(role_path)) && File.exists?(galaxy_install_info)
    YAML.load_file(galaxy_install_info)
  else
    { install_date: "", version: "0.0.0" }
  end
end

# Uses the contents of roles.txt to ensure that ansible-galaxy is run
# if any dependencies are missing
def install_dependent_roles
  ansible_directory = File.join("ansible")
  ansible_roles_txt = File.join(ansible_directory, "roles.txt")

  File.foreach(ansible_roles_txt) do |line|
    role_name, role_version = line.split(",")
    role_path = File.join(ansible_directory, "roles", role_name)
    galaxy_metadata = galaxy_install_info(role_name)

    if galaxy_metadata["version"] != role_version.strip
      unless system("ansible-galaxy install -f -r #{ansible_roles_txt} -p #{File.dirname(role_path)}")
        $stderr.puts "\nERROR: An attempt to install Ansible role dependencies failed."
        exit(1)
      end

      break
    end
  end
end

# Install missing role dependencies based on the contents of roles.txt
if [ "up", "provision", "status" ].include?(ARGV.first)
  install_dependent_roles
end

Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/trusty64"
  config.vm.box_check_update = true
  config.ssh.username = "vagrant"

  config.vm.define "default" do |db|
    db.vm.network :private_network, ip: "33.33.33.33"

    db.vm.synced_folder ".", "/vagrant/", type: "nfs"
    db.vm.synced_folder "src/app", "/opt/app/", type: "nfs"

    # Postgres
    db.vm.network "forwarded_port", guest: 5432, host: 5432

    # nginx
    db.vm.network "forwarded_port", guest: 80, host: 9090

    # VirtualBox settings
    db.vm.provider :virtualbox do |vb|
      vb.customize ["modifyvm", :id, "--memory", "2048"]
      vb.cpus = 8
    end

    # Settings for KVM/libvirt
    config.vm.provider :libvirt do |domain|
      domain.memory = 2048
    end

    # Ansible provisioning
    db.vm.provision :ansible do |ansible|
      ansible.playbook = "ansible/site.yml"
      ansible.sudo = true
    end
  end
end
