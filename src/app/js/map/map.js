'use strict'
var map = (function($, L) {

    var theMarker;
    var controlsOn = false;
    var module = {
        map: null,
        init: initiate,
        setMarker: setMarker
    };

    /**
     * Initiate to target location
     *
     * @param loc {array} Array of lnglat
     * @param zoom {int} The leaflet zoom level
     * @return {undefined}
     */
    function initiate(loc, zoom) {
        module.map = L.map('map', {zoomControl: false});
        module.map.addControl(L.control.zoom({position: 'topright'}));
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
    function setMarker(loc) {
        // add a marker in the given location, attach some popup content to it and open the popup
        if (theMarker) {
            theMarker.setLatLng(loc);
        } else {
            theMarker = L.marker(loc);
            theMarker.addTo(module.map);
        }
    }

    return module;
})(jQuery, L);
