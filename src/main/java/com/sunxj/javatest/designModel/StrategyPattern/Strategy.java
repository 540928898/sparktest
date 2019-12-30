package com.sunxj.javatest.designModel.StrategyPattern;

public class Strategy {


    public static void main(String[] args) {
        StrategySelect n1 = new StrategySelect("Spring");
        n1.StrategyShow();
        StrategySelect n2 = new StrategySelect("Mid");
        n2.StrategyShow();
        StrategySelect n3 = new StrategySelect("Chrimas");
        n3.StrategyShow();
    }
}
abstract class StrategyFestival {
    public abstract void Show();
}
class SpringStrategy extends  StrategyFestival{
    @Override
    public void Show() {
        System.out.println("SpringStrategy");
    }
}
class MidMoonStrategy extends  StrategyFestival{
    @Override
    public void Show() {
        System.out.println("MidFestival");
    }
}
class  ChrismasStrategy extends  StrategyFestival{
    @Override
    public void Show() {
        System.out.println("ChrismasStrtegy!");
    }
}
class StrategySelect{
    private StrategyFestival mySelectStrategy ;
    public StrategySelect(String selection){
        switch (selection){
            case "Spring":
                mySelectStrategy = new SpringStrategy();
                break;
            case "Mid":
                mySelectStrategy = new MidMoonStrategy();
                break;
            case "Chrimas":
                mySelectStrategy = new ChrismasStrategy();
                break;
            default:
                mySelectStrategy = null;
        }
    }
    public void StrategyShow(){
        mySelectStrategy.Show();
    }
}