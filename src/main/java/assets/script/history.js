/*
	jQuery document ready.
*/
$(document).ready(function()
{
    $("#sel1").on("change",
        function (){
            $( "#history_div" ).html("");
            var id = $(this).children(":selected").attr("id");
                $.ajax(
                {
                    type: "POST",
                    url: "/history/" + id
                }).done(function(playerGames)
                {
                   $.each(playerGames, function(k,playerGame)
                   {
                        $( "#history_div" ).append('<p>' + playerGame.player.username+ '</p>');
                         $.each(playerGame.hand.cards, function(k,card)
                         {
                            $( "#history_div" ).append('<img class="img_card img-thumbnail" src="/assets/images/cards/' + card.rank + '_' + card.suit + '.png" style="width:100px;height:145px">');
                         });
                         $( "#history_div" ).append('<br>');
                         $( "#history_div" ).append('<h3>' + playerGame.hand.handType +' = '+ playerGame.result + '</h4></h3>');
                         $( "#history_div" ).append('<hr>');
                         $( "#history_div" ).append('<br>');
                   });
                });
            });
});
