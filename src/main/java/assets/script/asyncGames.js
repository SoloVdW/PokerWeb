/*
	jQuery document ready.
*/
$(document).ready(function()
{
    function showGames(games){
        $( "#showOpenGames" ).html("");
         $.each(games, function(k,game)
         {
            $( "#showOpenGames" ).append('<li id=' + game.id +' href="#" class="list-group-item">' + game.id + ' : '+ game.dateTime + '</li>');
         });
    };

      function updateGameList(){
                $.ajax(
                {
                    type: "GET",
                    url: "/getGameList"
                }).done(function(games)
                {
                    showGames(games);
                    updateGameList();
                });
            };
    updateGameList();
});