$(function() {

function readURLFull(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#preview-img-full').attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

$("#input-select-img-full").change(function() {
    readURLFull(this);
});

function readURLIcon(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#preview-img-icon').attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

$("#input-select-img-icon").change(function() {
    readURLIcon(this);
});

function readURLThumb(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#preview-img-thumb').attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

$("#input-select-img-thumb").change(function() {
    readURLThumb(this);
});

});