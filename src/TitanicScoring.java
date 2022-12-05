import java.util.Arrays;

public class TitanicScoring {

	public static double ALPHA = 1;

	public static double[] weights = {
			-5.484844451041736, -7.605450602165511, -0.5726490004995397, 27.04005963262653, -0.02427268329054242, -1.2369238305370738, -1.1267246031054758, 0.1612078218380119
	};

	public static NormalizedTitanic.NormalizedPassenger[] passengers = TitanicClassifier.normalize(TitanicTrainData.passengers);

	public static void print(NormalizedTitanic.NormalizedPassenger passenger) {
		System.out.println("  ID = " + passenger.id());
		System.out.println("  Sex = " + passenger.sex());
		System.out.println("  Age = " + passenger.age());
		System.out.println("  Port = " + passenger.port());
		System.out.println("  Fare = " + passenger.fare());
		System.out.println("  Class = " + passenger.pclass());
		System.out.println("  Parents = " + passenger.parents());
		System.out.println("  Siblings = " + passenger.siblings());
		System.out.println("  Survived = " + passenger.survived());
		System.out.println();
	}

	public static void main(String[] args) {
		Titanic.Attribute[] attributes = Titanic.Attribute.values();

		double loss = 100;
		while (loss > 0.17) {
			System.out.println(Arrays.toString(weights));
			System.out.println(loss);
			loss = 0;
			double[] weightChanges = new double[attributes.length + 1];
			for (int i = 0; i < passengers.length; i++) {
				double predicted = 0;
				predicted += weights[0];
				for (int j = 0; j < attributes.length; j++) {
					predicted += weights[j + 1] * passengers[i].get(attributes[j]);
				}
				double sigmoid = 1 / (1 + Math.exp(-predicted));
				double difference = (passengers[i].survived() ? 1 : 0) - sigmoid;
				loss += Math.pow(difference, 2) / (double) passengers.length;

				weightChanges[0] = weights[0] + ALPHA * difference * sigmoid * (1 - sigmoid); // bias
				for (int j = 0; j < attributes.length; j++) {
					double value = weights[j + 1] + ALPHA * difference * sigmoid * (1 - sigmoid) * passengers[i].get(attributes[j]);
					weightChanges[j + 1] += value / (double) passengers.length;
				}
			}
			weights = weightChanges;
		}


		int correct = 0;
		int count = 0;
		
		for (NormalizedTitanic.NormalizedPassenger passenger : TitanicClassifier.normalize(TitanicTestData.passengers)) {
			try {
				boolean survived = TitanicClassifier.survived(passenger);
				if (survived == passenger.survived()) {
					correct++;
				} else {
					System.out.println("Incorrect classification: ");
					print(passenger);
				}
			} catch (Exception e) {
				System.out.println("Unhandled exception (" + e + "): ");
				print(passenger);
			}
			count++;
		}

		double accuracy = ((double) correct) / ((double) count);
		System.out.printf("Accuracy = %.2f\n", 100.0 * accuracy);
	}
}
