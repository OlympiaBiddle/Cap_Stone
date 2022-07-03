/**
 * 
 */
 
console.log('this is my js');

/**
 * this code toggles the the side bar of the contacts page
 */
 
 
  
 const toggleSidebar=()=>{

    if($('.sidebar').is(':visible')){

        //true
        //close side bar
        $('.sidebar').css('display','none')
        $('.content').css('margin-left','0%')

    }else{
        //false
        //open side bar
        $('.sidebar').css('display','block')
        $('.content').css('margin-left','20%')
    }
    console.log("i am working");
};


