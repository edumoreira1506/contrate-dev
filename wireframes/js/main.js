$(function(){
	$('#creation').fadeIn();

	$('#aproov').animate({
		left: '+=180'
	}, 500);

	$('#aproov ul').jcarousel({
		vertical: true,
		scroll: 1
	});

	$('#hiddenIcon').click(function(){
		if($('#hiddenIcon').hasClass('hidden')) {
			$('#aproov').animate({
				left: '+=180'
			}, 500);
		} else {
			$('#aproov').animate({
				left: '-=180'
			}, 500);
		}

		$('#hiddenIcon').toggleClass('hidden');
	});

	$('#aproov ul li img').click(function(){
		$(window).scrollTop(0);
		var src = $(this).attr('src');
		$('#aproov ul li img').removeClass('choiced');
		$(this).addClass('choiced');
		$('#creation img').remove();
		$('#creation').append('<img src="' + src + '" />').css('display', 'none').fadeIn();
	});
});