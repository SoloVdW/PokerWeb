/*
	jQuery document ready.
*/
$(document).ready(function()
{
    function showPlayers(game){
        $( "#playerGroup" ).html("");
        $.each(game.playerGames, function(k,playerGame)
        {
            $( "#playerGroup" ).append('<li class="list-group-item active">' + playerGame.player.username+ '</li>');
        });
    };

      function updateGame(){
                $.ajax(
                {
                    type: "GET",
                    url: "/getUpdatedGame"
                }).done(function(game)
                {
                    if (game.status === "COMPLETE")
                    {
                        window.location.replace("/game/" + game.id + "/play");
                    }
                    showPlayers(game);
                    updateGame();
                });
            };
    updateGame();
});