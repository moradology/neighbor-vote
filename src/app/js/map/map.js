'use strict'
var map = (function() {

    var module = {
        map: null,
        init: initiate,
        addMarker: addMarker
    };

    /**
     * Initiate to target location
     *
     * @param loc {array} Array of lnglat
     * @param zoom {int} The leaflet zoom level
     * @return {undefined}
     */
    function initiate(loc, zoom) {
        module.map = L.map('map');
        module.map.setView(loc, zoom);
        L.tileLayer('http://{s}.tile.stamen.com/toner/{z}/{x}/{y}.{ext}', {
          Arrayttribution: 'Map tiles by <a href="http://stamen.com">Stamen Design</a>, <a href="http://creativecommons.org/licenses/by/3.0">CC BY 3.0</a> &mdash; Map data &copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
          subdomains: 'abcd',
          minZoom: 0,
          maxZoom: 20,
          ext: 'png'
        }).addTo(module.map);
    }

    /**
     * Add a marker on the map
     *
     * @param loc {array} Array of lnglat
     * @return {undefined}
     */
    function addMarker(loc) {
        // add a marker in the given location, attach some popup content to it and open the popup
        L.marker(loc).addTo(module.map);
    }

    return module;
})();
