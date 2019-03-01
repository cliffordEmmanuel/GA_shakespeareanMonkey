public class DNA {

    protected char genes[];
    protected double fitness = 0.0;
    protected String target;

//    DNA(){
//        for (int i =0; i< genes.length; i++){
//            genes[i] = (char) random(63,122);
//        }
//    }


    DNA(String target) {
        this.target = target;
        this.genes = new char[target.length()];

        for (int i = 0; i < genes.length; i++) {
            genes[i] = (char) random(63, 122);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(genes);
    }


    private double random(int num) {
        return (Math.random() * num);
    }


    private int random(int min, int max) {
        int rn = (int) ((Math.random() * ((max - min) + 1)) + min);
        if (rn == 63) rn = 32;
        if (rn == 64) rn = 46;
        return rn;
    }

    void fitness() {
        int score = 0;
        for (int i = 0; i < genes.length; i++) {
            if (genes[i] == target.charAt(i)) {
                score++;
            }
        }
        fitness = score / (double) target.length();     //this is the percentage of the characters present...
    }

    // The function receives one argument (DNA) and returns DNA.
    DNA crossover(DNA partner) {
        // The child is a new instance of DNA.
        // Note that the DNA is generated randomly in the constructor,
        // but we will overwrite it below with DNA from parents.
        DNA child = new DNA(this.target);

        // Picking a random “midpoint” in the genes array
        int midpoint = (int) random(genes.length);

        for (int i = 0; i < genes.length; i++) {
            //[full] Before midpoint copy genes from one parent, after midpoint copy genes from the other parent
            if (i > midpoint) child.genes[i] = genes[i];
            else child.genes[i] = partner.genes[i];
            //[end]
        }
        // Return the new child DNA
        return child;
    }

    void mutate(double mutationRate) {
        for (int i = 0; i < genes.length; i++) {
            if (random(1) < mutationRate) genes[i] = (char) random(63, 123);
        }
    }
}
