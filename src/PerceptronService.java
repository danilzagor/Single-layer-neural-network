import java.util.Collections;

public class PerceptronService {
    private final DataRepository dataRepository;
    private double alpha;
    private final String language;
    private double threshold;
    private double[] weights;
    private final double expectedOutput;
    private final int numberOfCycles;


    public PerceptronService(DataRepository dataRepository, String language, double learningConst, int numberOfCycles) {
        this.dataRepository = dataRepository;
        this.language = language;
        this.alpha = learningConst;
        this.threshold = 5;
        this.expectedOutput = 0.8;
        this.numberOfCycles = numberOfCycles;
        setDefaultValuesForWeights();
        trainThePerceptron();
    }

    private void setDefaultValuesForWeights() {
        weights = new double[26];
        for (int i = 0; i < 26; i++) {
            weights[i] = 1.0/26;
        }
    }

    private void trainThePerceptron() {
        for(int i=0; i<numberOfCycles; i++) {
            Collections.shuffle(dataRepository.getTexts());
            dataRepository.getTexts().forEach(text -> {
                if (!isCorrectlyClassified(text)) {
                    deltaFormula(text);
                }
            });
        }
    }

    private boolean isCorrectlyClassified(TextEntity text) {
        if (perceptronOutput(text) >= expectedOutput && text.getLanguage().equals(language))
            return true;
        else {
            return perceptronOutput(text) < expectedOutput && !text.getLanguage().equals(language);
        }
    }


    public double makeTest(String textToTest) {
        return perceptronOutput(new TextEntity("", textToTest));
    }

    private double perceptronOutput(TextEntity textEntity) {
        double net = 0;
        for (int i = 0; i < textEntity.getAlphabetCount().length; i++) {
            double x = textEntity.getAlphabetCount()[i];
            net += weights[i] * x;
        }
        net -= threshold;
        return 1 / (1 + Math.pow(Math.E, -1 * net));
    }

    private void thresholdChange(double d, double y) {
        threshold = threshold + (d - y)  * alpha *  (-1);
    }

    private void deltaFormula(TextEntity text) {
        double y = perceptronOutput(text);
        double d = text.getLanguage().equals(language) ? 1 : 0;
        for (int i = 0; i < text.getAlphabetCount().length; i++) {
            double w = weights[i];
            double x = text.getAlphabetCount()[i];
            double deltaWeights = w + (d - y)  * alpha * x;
            weights[i] = deltaWeights;
        }
        thresholdChange(d, y);
    }


    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }


    public double[] getWeights() {
        return weights;
    }


    public String getLanguage() {
        return language;
    }
}
