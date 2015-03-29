'use strict';

var landing = (function(){

    var module = {
        init: initiate
    };

    function initiate(){
      $('#greeting').modal({
          backdrop: 'static',
          keyboard: false
      });
    }


    return module;
})();
