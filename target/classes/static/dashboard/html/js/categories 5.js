$(document).ready(function () {

    $("#btn-save").on("click", function () {

        var category ={};
        if($("#name").val() === "" || $("#link").val() === "") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }
        category.name = $('#name').val();
        category.link = $('#link').val();
        category.note = $('#note').val();
        category.description = $('#description').val();


        NProgress.start();

        var linkPost = "/api/categories/insert";
        axios.post(linkPost, category).then(function(res){
            NProgress.done();
            if(res.data.responseCode == '00') {
                swal(
                    'Good job!',
                    res.data.responseDesc,
                    'success'
                ).then( function(){
                     location.reload();
                });
            } else {
                swal(
                    'Error',
                    res.data.responseDesc,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Some error when saving Categories',
                'error'
            );
        })
    });

    $('.categoryDetail').click(function () {
        var catId = $(this).data('catdetail');
        NProgress.start();
        axios.get("/api/categories/detail/"+catId).then(function (res) {
            NProgress.done();
            if(res.data.responseCode == '00'){
                $('#idEdit').val(res.data.data.id);
                $('#nameEdit').val(res.data.data.name);
                $('#linkEdit').val(res.data.data.link);
                $('#descriptionEdit').val(res.data.data.description);
                $('#statusEdit').val(res.data.data.status);
                $('#noteEdit').val(res.data.data.note);
                if(res.data.data.status == 1){
                    $('#active').prop('checked',true)
                    $('#deactive').prop('checked',false)
                }else {
                    $('#deactive').prop('checked',true)
                    $('#active').prop('checked',false)
                }
                $("#editModal").modal();
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Some error when getting Categories',
                'error'
            );
        })
    });


    $("#btn-saveEdit").on("click", function () {
       var catId = $('#idEdit').val();
       var category ={}
        if($("#nameEdit").val() === "" || $("#linkEdit").val() === "") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }
        category.id = catId;
        category.name = $('#nameEdit').val();
        category.link = $('#linkEdit').val();
        category.note = $('#noteEdit').val();
        category.description = $('#descriptionEdit').val();
        if($("#active").is(':checked')){
            category.status = 1
        } else {
            category.status = 2
        }



        NProgress.start();
        var linkPost = '';
        if(category.id) {
            linkPost = "/api/categories/update/" + category.id;
        }

        axios.post(linkPost, category).then(function(res){
            NProgress.done();
            if(res.data.responseCode == '00') {
                swal(
                    'Good job!',
                    res.data.responseDesc,
                    'success'
                ).then( function(){
                    location.reload();
                });
            } else {
                swal(
                    'Error',
                    res.data.responseDesc,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Some error when saving Categories',
                'error'
            );
        })
   });
})