public class TitanicClassifier {

	static Titanic.Attribute[] attributes = Titanic.Attribute.values();

	public static NormalizedTitanic.NormalizedPassenger[] normalize(Titanic.Passenger[] rawData) {
		double[] averages = new double[attributes.length];
		for (int i = 0; i < attributes.length; i++) {
			for (Titanic.Passenger passenger : rawData) {
				averages[i] += passenger.get(attributes[i]) / (double) rawData.length;
			}
		}

		double[] stdevs = new double[attributes.length];
		for (int i = 0; i < attributes.length; i++) {
			double sum = 0;
			for (Titanic.Passenger passenger : rawData) {
				sum += Math.pow(passenger.get(attributes[i]) - averages[i], 2);
			}
			stdevs[i] = Math.sqrt(sum / (double) rawData.length);
		}

		NormalizedTitanic.NormalizedPassenger[] normalizedData = new NormalizedTitanic.NormalizedPassenger[rawData.length];
		for (int i = 0; i < normalizedData.length; i++) {
			normalizedData[i] = new NormalizedTitanic.NormalizedPassenger(rawData[i].survived());
			for (int j = 0; j < attributes.length; j++) {
				normalizedData[i].set(attributes[j], (rawData[i].get(attributes[j])) - averages[j] / stdevs[j]);
			}
		}
		return normalizedData;
	}

	public static boolean survived(NormalizedTitanic.NormalizedPassenger passenger) {
		double predicted = 0;
		predicted += TitanicScoring.weights[0];
		for (int j = 0; j < attributes.length; j++) {
			predicted += TitanicScoring.weights[j + 1] * passenger.get(attributes[j]);
		}
		double sigmoid = 1 / (1 + Math.exp(-predicted));

		return sigmoid > 0.5;
	}
}
