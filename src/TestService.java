import java.util.ArrayList;
import java.util.List;

public class TestService {
    private final List<PerceptronService> perceptronList = new ArrayList<>();


    public TestService(DataRepository dataRepository, double learningConst, int numberOfCycles) {
        makePeceptronList(dataRepository, learningConst, numberOfCycles);
    }



    private void makePeceptronList(DataRepository dataRepository, double learningConst, int numberOfCycles) {
        for (String language : dataRepository.getLanguages()) {
            final PerceptronService perceptronService = new PerceptronService(dataRepository, language, learningConst, numberOfCycles);
            perceptronList.add(perceptronService);
        }
    }

    public String languageOfText(String text) {
        String result="Nie udalo sie rospoznac jezyk, sproboj jeszcze raz, lub dodaj wiecej slow";
        double maxActivation = 0;
        for (PerceptronService perceptronService : perceptronList) {
            double perceptronActivation =  perceptronService.makeTest(text);
            if (perceptronActivation > maxActivation){
                maxActivation = perceptronActivation;
                result = perceptronService.getLanguage();
            }
        }
        return result;
    }
}
