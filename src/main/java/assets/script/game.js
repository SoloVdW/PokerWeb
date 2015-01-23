/*
	jQuery document ready.
*/
$(document).ready(function()
{
    $("#deal_hands").on("click",
        function (){
             $( "#deal_div" ).html("");
                             $.ajax(
                             {
                                 type: "POST",
                                 url: "/deal"
                             }).done(function(playerGames)
                             {
                                $.each(playerGames, function(k,v)
                                {
                                    $.each(v, function(k,playerGame)
                                    {
                                        $( "#deal_div" ).append('<p>' + playerGame.player.username+ '</p>');
                                        $.each(playerGame.hand.cards, function(k,card)
                                        {
                                            $( "#deal_div" ).append('<img class="img_card img-thumbnail" src="/assets/images/cards/' + card.rank + '_' + card.suit + '.png">');
                                        });
                                        $( "#deal_div" ).append('<br>');
                                        $( "#deal_div" ).append('<h3>' + playerGame.hand.handType +' = '+ playerGame.result + '</h4></h3>');
                                        $( "#deal_div" ).append('<hr>');
                                        $( "#deal_div" ).append('<br>');
                                    });
                                });
                             });
                         });
             });