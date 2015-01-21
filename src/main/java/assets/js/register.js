/*
	jQuery document ready.
*/
 $( document ).ready(function()
        {
            $("#username").keyup(function()
            {
                var thisusername = $("#username").val();

                $.ajax(
                {
                    type: "POST",
                    url: "/user/" + thisusername
                }).done(function(data)
                {
                    if (data.exists)
                    {
                        $('input[type="submit"]').attr('disabled','disabled');
                        $("#message").removeAttr('style');
                        $("#username").css("background-image","linear-gradient(to bottom, #D9534F 0px, #C9302C 100%)");
                        //$("#username").css("background","red");
                    }
                    else
                    {
                        $('input[type="submit"]').removeAttr('disabled');
                        $("#message").attr('style','display: none');
                        $("#username").css("background-image", "linear-gradient(to bottom, white 0px, white 100%)");
                        //$("#username").css("background","white");
                    }
                });
            });

            $('#password').keyup(function()
            	{
            		$('#result').html(checkStrength($('#password').val()))
            	})

            	/*
            		checkStrength is function which will do the
            		main password strength checking for us
            	*/

            	function checkStrength(password)
            	{
            		//initial strength
            		var strength = 0
					var strengthPrecentage= 0;
            		//if the password length is less than 6, return message.
            		if (password.length < 6) {
            			$("#password").css("background-image","linear-gradient(to bottom, #D9534F 0px, #C9302C 100%)");

            			strengthPrecentage= 33 / (6 - password.length);
            			$("#progressBar").attr('aria-valuenow',strengthPrecentage);
                        $("#progressBar").attr('style','width:' + strengthPrecentage + '%');

                        $('#progressBar').removeClass()
                        $('#progressBar').addClass('progress-bar progress-bar-danger')

            			return 'Short'
            		}

            		//length is ok, lets continue.

            		//if length is 8 characters or more, increase strength value
            		if (password.length > 7) strength += 1

            		//if password contains both lower and uppercase characters, increase strength value
            		if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/))  strength += 1

            		//if it has numbers and characters, increase strength value
            		if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/))  strength += 1

            		//if it has one special character, increase strength value
            		if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/))  strength += 1

            		//if it has two special characters, increase strength value
            		if (password.match(/(.*[!,%,&,@,#,$,^,*,?,_,~].*[!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1

            		//now we have calculated strength value, we can return messages

            		//if value is less than 2
            		if (strength < 2 )
            		{
            			$("#password").css("background-image","linear-gradient(to bottom, #F0AD4E 0px, #EC971F 100%)");
            			$("#progressBar").attr('aria-valuenow',45);
                        $("#progressBar").attr('style','width:' + 45 + '%');

                        $('#progressBar').removeClass()
                        $('#progressBar').addClass('progress-bar progress-bar-warning')
            			return 'Weak'
            		}
            		else if (strength == 2 )
            		{
            			$("#password").css("background-image","linear-gradient(to bottom, #5BC0DE 0px, #31B0D5 100%)");

            			$("#progressBar").attr('aria-valuenow',75);
                        $("#progressBar").attr('style','width:' + 75 + '%');

            			$('#progressBar').removeClass()
                        $('#progressBar').addClass('progress-bar progress-bar-info')
            			return 'Good'
            		}
            		else
            		{
            			$("#password").css("background-image","linear-gradient(to bottom, #5CB85C 0px, #449D44 100%)");

            			$("#progressBar").attr('aria-valuenow',100);
                        $("#progressBar").attr('style','width:' + 100 + '%');

                        $('#progressBar').removeClass()
                        $('#progressBar').addClass('progress-bar progress-bar-success')
            			return 'Strong'
            		}
            	}
            });