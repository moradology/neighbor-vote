"use strict";

$(document).ready(function() {
    landing.init();

    map.init([39.9523893, -75.1636291], 12);
    map.setMarker([39.9523893, -75.1636291]);

    $('button#modal-dismiss').click(function() {
    });

    $('button#check-location').click(function() {
        $.ajax({
            method: 'POST',
        });
    });

});
