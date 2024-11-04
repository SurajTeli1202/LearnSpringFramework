package com.springcore;

import com.springcore.enterprise.example.web.MyWebController;
import com.springcore.game.GameRunner;
import com.springcore.game.MarioGame;
import com.springcore.game.Pacman;
import com.springcore.game.SuperContraGame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan("com.springcore.enterprise.example")
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );

        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
//        GamingConsole game = new Pacman();
//        GameRunner runner = new GameRunner(game);
        GameRunner runner = context.getBean(GameRunner.class);
        runner.run();
        MyWebController controller = context.getBean(MyWebController.class);
        System.out.println(controller.returnValueFromBusinessService());
    }
}
