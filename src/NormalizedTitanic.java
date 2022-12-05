public class NormalizedTitanic {

    public static class NormalizedPassenger {

        private int     id;
//        private String  name;
        private double  port;
        private double  pclass;
        private double  sex;
        private double  age;
        private double  siblings;
        private double  parents;
        private double  fare;
        private boolean survived;

        public NormalizedPassenger(
                int     id,
//                String  name,
                boolean survived,
                double  port,
                double  pclass,
                double  sex,
                double  age,
                double  siblings,
                double  parents,
                double  fare)
        {
            this.id       = id;
//            this.name     = name;
            this.survived = survived;
            this.port     = port;
            this.pclass   = pclass;
            this.sex      = sex;
            this.age      = age;
            this.siblings = siblings;
            this.parents  = parents;
            this.fare     = fare;
        }

        public NormalizedPassenger(boolean survived) {
            this.survived = survived;
        }

        int     id()       { return this.id; }
//        String  name()     { return this.name; }
        boolean survived() { return this.survived; }
        double    port()     { return this.port; }
        double  pclass()   { return this.pclass; }
        double  sex()      { return this.sex; }
        double  age()      { return this.age; }
        double  siblings() { return this.siblings; }
        double  parents()  { return this.parents; }
        double  fare()     { return this.fare; }

        public double get(Titanic.Attribute attribute) {
            // Returns the value of the specified attribute for this passenger.
            switch(attribute) {
                case CLASS:    return this.pclass;
                case PORT:     return this.port;
                case SEX:      return this.sex;
                case AGE:      return this.age;
                case SIBLINGS: return this.siblings;
                case PARENTS:  return this.parents;
                case FARE:     return this.fare;
            }
            throw new IllegalArgumentException("Invalid attribute: " + attribute);
        }

        public void set(Titanic.Attribute attribute, double value) {
            // Returns the value of the specified attribute for this passenger.
            switch(attribute) {
                case CLASS:    this.pclass = value; break;
                case PORT:     this.port = value; break;
                case SEX:      this.sex = value; break;
                case AGE:      this.age = value; break;
                case SIBLINGS: this.siblings = value; break;
                case PARENTS:  this.parents = value; break;
                case FARE:     this.fare = value; break;
                default: throw new IllegalArgumentException("Invalid attribute: " + attribute);
            }
        }
    }
}
