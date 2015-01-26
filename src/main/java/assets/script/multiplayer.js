/*
	jQuery document ready.
*/
$(document).ready(function()
{
    $("#join_game").on("click",
            function (){
                 $('#host_game_tab').removeClass();
                 $('#join_game_tab').removeClass();
                 $('#join_game_tab').addClass('active');
                 joinGame();
            });

    function hostNewGame(id){
        $.ajax(
        {
            type: "GET",
            url: "/host_game/" + id
        }).done(function(data)
        {
            window.location.replace("/game/" + data.gameId + "/play");
        });
    };

    function joinGame(){
    };

    $("#gameList").delegate('*','click',
                function (e){
                var id = e.target.id;
                     joinTheGame(id);
                });

    function joinTheGame(id){
            $.ajax(
            {
                type: "GET",
                url: "/join_game/" + id
            }).done(function(game)
            {
                window.location.replace("/game_lobby/" + game.id);

            });
        };

    function showPlayers(game){
        $( "#playerGroup" ).html("");
        $.each(game.playerGames, function(k,playerGame)
        {
            $( "#playerGroup" ).append('<li class="list-group-item active">' + playerGame.player.username+ '</li>');
        });
    };

      function updateGame(id){
                $.ajax(
                {
                    type: "GET",
                    url: "/getUpdatedGame"
                }).done(function(game)
                {
                    showPlayers(game);
                });
            };

    $("#start_game_div").delegate('*','click',
                    function (e){
                    var id = e.target.id;
                          playTheGame(id);
                    });

    function playTheGame(id){
                $.ajax(
                {
                    type: "GET",
                    url: "/playGame/" + id
                }).done(function(data)
                {
                    window.location.replace("/game/" + data.gameId + "/play");

                });
            };

    $("#start_new_game_div").delegate('*','click',
                        function (e){
                        var id = e.target.id;
                        hostNewGame(id);
                        });
});