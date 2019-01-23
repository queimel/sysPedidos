$( document ).ready(function() {
    
    $('.form-check-input').click(function(){
        $(this).parent().parent().toggleClass("active");
        
        /*if($(this).find('.form-check-input').is(':checked')){
            $(this).find('.form-check-input').prop('checked', true);
        }*/
    })
});