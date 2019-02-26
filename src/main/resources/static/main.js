$("body").on("click", ".register", function() {
	$("#registerModal").modal();
	$("#registerModal .register-modal").addClass("modal-color");
})

$("body").on("click", ".login", function() {
	$("#loginModal").modal();
	$("#loginModal .login-modal").addClass("modal-color");
})

$("body").on("click", ".icon-add", function() {
	$("#addModal").modal('show');
})

$(".btn-cart").click(function(event) {
//	var id = 19;
//	$.ajax({
//		url : "/addCart",
//		type : "GET",
//		data : {
//			id : id
//		},
//	
//		success : function () {
//			
//		}
//	})
	
//	alert(price);
})

$(".btn-delete-cart").click(function(event) {
	event.preventDefault();
	var href=$(this).find(".link-delete-cart").attr("href");
	$("#deleteCart").attr("href",href);
	$("#deleteCartModal").modal();
})

totalPriceCart();
    
    function totalPriceCart() {
    	var totalprice = 0;
    	$(".price-product").each(function() {
    		var giatien = $(this).text();
    		var quantity = $(this).closest("tr").find(".quantity").text();
    		var format = parseFloat(giatien).toFixed(6).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1.").toString();
    		
    		$(this).html(format);
    		
    		var total = quantity * parseFloat(giatien);
    		totalprice = totalprice + total;

    		var formattotalprice = totalprice.toFixed(6).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1.").toString();
    		$("#totalprice").html(formattotalprice + " VNĐ");
    	})
    }
    
    var checkAns = false;
    
$(".show-ans").click(function() {
	if (checkAns == false) {
		$(this).find("#hidden-ans").removeClass("hidden-ans");
		checkAns = true;
	} else {
		$(this).find("#hidden-ans").addClass("hidden-ans");
		checkAns = false;
	}
})
