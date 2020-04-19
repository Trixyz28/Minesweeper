package owo;

import owo.controller.Controller;
import owo.model.Model;
import owo.view.View;



public class App 
{
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model,view);

        view.addObserver(controller);
        model.addObserver(view);
        view.run();

    }
}
