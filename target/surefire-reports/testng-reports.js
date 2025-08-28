$(document).ready(function() {
    // Use event delegation for all navigator links
    $(document).on('click', 'a.navigator-link', function() {
        var panelName = $(this).attr('panel-name');
        $('.navigator-link').parent().removeClass('navigator-selected');
        $(this).parent().addClass('navigator-selected');
        showPanel(panelName);
    });

    installMethodHandlers('failed');
    installMethodHandlers('skipped');
    installMethodHandlers('passed', true); // hide passed methods by default

    // Use event delegation for method links
    $(document).on('click', 'a.method', function(e) {
        e.preventDefault();
        showMethod($(this));
    });

    // Hide all the panels and display the first one (do this last
    // to make sure the click() will invoke the listeners)
    $('.panel').hide();
    if ($('.navigator-link').length) {
        $('.navigator-link').first().trigger("click");
    }

    // Collapse/expand the suites
    $('a.collapse-all-link').on("click", function() {
        var contents = $('.navigator-suite-content');
        if (contents.css('display') == 'none') {
            contents.show();
        } else {
            contents.hide();
        }
    });
});

// The handlers that take care of showing/hiding the methods
function installMethodHandlers(name, hide) {
    var showMethodsLink = $(`.show-methods.${name}`);
    var hideMethodsLink = $(`.hide-methods.${name}`);

    hideMethodsLink.on("click", function() {
        var panelName = $(this).attr('panel-name');
        $(`.method-list-content.${name}.${panelName}`).hide();
        $(`.hide-methods.${name}.${panelName}`).hide();
        $(`.show-methods.${name}.${panelName}`).show();
        $(`.${panelName}-class-${name}`).hide();
    });

    showMethodsLink.on("click", function() {
        var panelName = $(this).attr('panel-name');
        $(`.method-list-content.${name}.${panelName}`).show();
        $(`.hide-methods.${name}.${panelName}`).show();
        $(`.show-methods.${name}.${panelName}`).hide();
        showPanel(panelName);
        $(`.${panelName}-class-${name}`).show();
    });

    if (hide) {
        hideMethodsLink.trigger("click");
    } else {
        showMethodsLink.trigger("click");
    }
}

function getPanelName(element) {
    return element.attr('panel-name');
}

function showPanel(panelName) {
    $('.panel').hide();
    var panel = $(`.panel[panel-name="${panelName}"]`);
    panel.show();
}

function showMethod(element) {
    var hashTag = getHashForMethod(element);
    var panelName = getPanelName(element);
    showPanel(panelName);
    // Use modern history API to avoid page jump and full reload
    history.pushState(null, null, '#' + hashTag);
    // Use scrollIntoView for smooth and reliable scrolling
    var method = document.getElementById(hashTag);
    if (method) {
        method.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
}

function drawTable() {
    for (var i = 0; i < suiteTableInitFunctions.length; i++) {
        window[suiteTableInitFunctions[i]]();
    }

    for (var k in window.suiteTableData) {
        var v = window.suiteTableData[k];
        var div = v.tableDiv;
        var data = v.tableData
        var table = new google.visualization.Table(document.getElementById(div));
        table.draw(data, {
            showRowNumber : false
        });
    }
}

function getHashForMethod(element) {
    return element.attr('hash-for-method');
}
