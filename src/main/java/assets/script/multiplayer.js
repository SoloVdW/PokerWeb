/*
	jQuery document ready.
*/
$(document).ready(function()
{
    $("#host_game").on("click",
        function (){
            $('#host_game_tab').removeClass();
            $('#join_game_tab').removeClass();
            $('#host_game_tab').addClass('active');
            hostGame();
        });

    $("#join_game").on("click",
            function (){
                 $('#host_game_tab').removeClass();
                 $('#join_game_tab').removeClass();
                 $('#join_game_tab').addClass('active');
                 joinGame();
            });

    function hostGame(){
        /*$.ajax(
        {
            type: "GET",
            url: "/host_game"
        }).done(function(data)
        {
            alert(data.gameId);
        });*/
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
               /* $('#showOpenGames').attr('style','display: none;');
                $("#showPlayers").removeAttr('style');*/
                window.location.replace("game_lobby/" + game.id);

            });
        };

    function showPlayers(game){
        $( "#playerGroup" ).html("");
        $.each(game.playerGames, function(k,playerGame)
        {
            $( "#playerGroup" ).append('<li class="list-group-item active">' + playerGame.player.username+ '</li>');
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
                   /* $('#showOpenGames').attr('style','display: none;');
                    $("#showPlayers").removeAttr('style');*/
                    window.location.replace("game/" + data.gameId + "/play");

                });
            };
});