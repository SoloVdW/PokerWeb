/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package conf;


import controllers.*;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {
        router.GET().route("/login").with(AuthenticationController.class, "login");
        router.POST().route("/login").with(AuthenticationController.class, "login");
        router.GET().route("/register").with(AuthenticationController.class, "register");
        router.GET().route("/logout").with(AuthenticationController.class, "logout");
        router.POST().route("/register").with(AuthenticationController.class, "register");
        router.POST().route("/user/{name}").with(UserController.class, "exists");
        router.GET().route("/user/{name} ").with(UserController.class, "exists");

        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController.class, "serveWebJars");
        router.GET().route("/assets/{fileName: .*}").with(AssetsController.class, "serveStatic");

        router.GET().route("/test").with(TestController.class, "test");

        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/").with(GameController.class, "index");
        router.GET().route("/deal").with(GameController.class, "index");
        router.POST().route("/deal").with(GameController.class, "dealHands");

        router.GET().route("/history").with(GameController.class, "history");
        router.GET().route("/history/{id}").with(GameController.class, "getGameHistory");
        router.POST().route("/history/{id}").with(GameController.class, "getGameHistory");


        router.GET().route("/multiplayer").with(MultiplayerController.class,"index");
        router.GET().route("/host_game").with(MultiplayerController.class,"hostGame");
        router.GET().route("/join_game").with(MultiplayerController.class,"showGames");
        router.GET().route("/join_game/{id}").with(MultiplayerController.class,"joinGame");
        router.GET().route("/game_lobby/{id}").with(MultiplayerController.class,"gameLobby");
        router.GET().route("/game/{id}/play").with(MultiplayerController.class,"showGame");
        router.GET().route("/playGame/{id}").with(MultiplayerController.class,"playGame");

        router.GET().route("/getUpdatedGame").with(AsyncController.class,"getUpdatedGame");
        router.GET().route("/getGameList").with(AsyncController.class,"getGameList");
    }

}
