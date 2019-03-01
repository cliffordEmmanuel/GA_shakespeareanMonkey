import java.util.ArrayList;

public class Population {

    DNA[] population;
    ArrayList<DNA> matingPool = new ArrayList<>();
    double mutationRate;
    String target;

    int generations = 0;


    Population(double mutationRate, String target, int maxPop) {
        this.target = target;
        this.mutationRate = mutationRate;
        population = new DNA[maxPop];
        for (int i = 0; i < population.length; i++) {
            population[i] = new DNA(this.target);
        }
    }

    void selection() {
        for (int i = 0; i < population.length; i++) {    //calculates the fitness for all members of the population
            population[i].fitness();
        }

        //build the mating pool...
        for (int i = 0; i < population.length; i++) {
            int n = (int) (population[i].fitness * 100);
            for (int j = 0; j < n; j++) {
                matingPool.add(population[i]);
            }
        }
    }


    //calculating average fitness.....
    double averageFitness() {
        double sum = 0.0;
        for (DNA x : population) {
            x.fitness();
            sum += x.fitness;
        }
        return Math.floor((sum / population.length) * 100);
    }

    boolean found() {
        boolean found = false;
        for (DNA x : population) {
            x.fitness();
            if (x.fitness == 1.0) {
                found = true;
                System.out.println(x.toString() + "\nAt an average fitness of: ");
            }
        }
        return found;
    }


    //as well as build a mating pool

    void reproduce() {
        for (int i = 0; i < population.length; i++) {
            //two random numbers to randomly select 2 parents from the mating pool...
            int a = (int) (Math.random() * matingPool.size());
            int b = (int) (Math.random() * matingPool.size());
            DNA partnerA = matingPool.get(a);
            DNA partnerB = matingPool.get(b);
            DNA child = partnerA.crossover(partnerB);           //crossover
            child.mutate(mutationRate);
            population[i] = child;
        }
        generations++;
        matingPool.clear();
    }

    void display() {
        /*
        display the average fitness
        the total number of generations
        mutation rate
         */
        System.out.println("Average Fitness: " + this.averageFitness());
        System.out.println("Total generations: " + this.generations);
        System.out.println("Mutation rate: " + this.mutationRate);
        System.out.println("Population size: " + this.population.length);

    }
}
