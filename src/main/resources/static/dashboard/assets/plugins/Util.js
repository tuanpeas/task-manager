function convertEpochTime(epochTime) {
    if (epochTime === '' || epochTime === null) {
        return '';
    }
    return moment.utc(epochTime).local().format("YYYY-MM-DD HH:mm:ss")
}

function getCurrentDateFormatYYYYMMdd() {
    return moment().format('YYYY-MM-DD');
}

//truyen vao element id va flag = true / false, trang thai disable tuong ung
function disableElementId(id, flag) {
    $("#" + id).prop("disabled", flag);
}

function hiddenElementId(id) {
    $("#" + id).hide();
}

$(document).ready(function () {
    //Datetime Setting
    if ($('.mydatepicker').length) {
        $('.mydatepicker').datetimepicker({
            format: 'YYYY-MM-DD'
        });
    }
    if ($('.datepicker-autoclose').length) {
        $('.datepicker-autoclose').datetimepicker({
            format: 'YYYY-MM-DD'
        });
    }
    if ($('.datetimepicker').length) {
        $('.datetimepicker').datetimepicker({
            format: 'YYYY-MM-DD HH:mm:ss'
        });
    }
    $.fn.datepicker.dates['qtrs'] = {
        days: ["Sunday", "Moonday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
        daysShort: ["Sun", "Moon", "Tue", "Wed", "Thu", "Fri", "Sat"],
        daysMin: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"],
        months: ["Q1", "Q2", "Q3", "Q4", "", "", "", "", "", "", "", ""],
        monthsShort: ["Jan&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Feb&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mar", "Apr&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;May&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Jun", "Jul&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Aug&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sep", "Oct&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nov&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dec", "", "", "", "", "", "", "", ""],
        today: "Today",
        clear: "Clear",
        format: "mm/dd/yyyy",
        titleFormat: "MM yyyy",
        /* Leverages same syntax as 'format' */
        weekStart: 0
    };

    $('.quarterPicker').datepicker({
        format: "MM yyyy",
        minViewMode: 1,
        autoclose: true,
        language: "qtrs",
        forceParse: false
    }).on("show", function(event) {

        $(".month").each(function(index, element) {
            if (index > 3) $(element).hide();
        });

    });
    $('.monthPicker').datetimepicker({
        format: 'MM/YYYY'
    });

});

