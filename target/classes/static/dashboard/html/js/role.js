
$(document).ready(function () {

    $("#btn-saveRole").on("click", function () {

        var role ={};
        if($("#nameRole").val() === "") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }
        role.name = $('#nameRole').val();
        NProgress.start();

        var linkPost = "/api/role/insert";
        axios.post(linkPost, role).then(function(res){
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
                'Some error when saving Role',
                'error'
            );
        })
    });

    $('.roleDetail').click(function () {
        var roleId = $(this).data('roledetail');
        NProgress.start();
        axios.get("/api/role/detail/"+roleId).then(function (res) {
            NProgress.done();
            if(res.data.responseCode == '00'){
                $('#idRoleEdit').val(res.data.data.id);
                $('#nameRoleEdit').val(res.data.data.name);
                $("#editRoleModal").modal();
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Some error when getting Role',
                'error'
            );
        })
    });


    $("#btn-saveRoleEdit").on("click", function () {
        var roleId =$('#idRoleEdit').val();
        var role ={};
        if($("#nameRoleEdit").val() === "") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }
        role.id = roleId;
        role.name = $('#nameRoleEdit').val();



        NProgress.start();
        var linkPost = '';
        if(role.id) {
            linkPost = "/api/role/update/" + role.id;
        }

        axios.post(linkPost, role).then(function(res){
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
                'Some error when saving Role',
                'error'
            );
        })
    });


    $('#btn-set-role').click(function () {
        var arr=[];
        $('.ul-list-selected').each(function(){// id of ul
            $(this).find('li').each(function(){
                arr.push($(this).text() );
            });
        });
        var linkPost = "/api/role/update/set-role";
        axios.post(linkPost, arr).then(function(res){
            NProgress.done();
            if(res.data.responseCode === '00') {
                swal(
                    'Good job!',
                    res.data.responseDesc,
                    'success'
                ).then( function(){
                    window.location.href  = Constanst.GLOBAL_URL + 'logout';
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
                'Some error when saving Role',
                'error'
            );
        })
    })

})