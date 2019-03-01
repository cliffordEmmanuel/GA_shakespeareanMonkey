public class Setup {
    public static void main(String[] args) {
        Population pop = new Population(0.01, "to be or not to be", 200);
        //System.out.println(pop.target.length());
        while (!pop.found()) {
            pop.selection();
            pop.reproduce();
        }
        System.out.println(pop.averageFitness());
        pop.display();
    }
}
