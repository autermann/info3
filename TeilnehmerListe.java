import java.util.*;
import java.util.stream.Collectors;

public class TeilnehmerListe {
    public static void main(String[] args) {
        Vorlesung vorlesung = new Vorlesung(new Person("Müller-Olm"),
                "Software Engineering",
                new Uebungsgruppe(new Person("tutor1"),
                        new Teilnehmer("anton", 1),
                        new Teilnehmer("gregor", 2),
                        new Teilnehmer("johannes", 2),
                        new Teilnehmer("igor", 3),
                        new Teilnehmer("simon", 8),
                        new Teilnehmer("else", 7)),
                new Uebungsgruppe(new Person("tutor2"),
                        new Teilnehmer("max", 4),
                        new Teilnehmer("horst", 5),
                        new Teilnehmer("tim", 6)));
        System.out.printf("### Teilnehmerliste der Vorlesung '%s' gehalten von '%s'\n### Name (Matrikelnummer)\n",
                        vorlesung.getTitel(), vorlesung.getDozent().getName());
        vorlesung.getTeilnehmer().forEach(System.out::println);
    }


    public static class Person {
        private String name;

        public Person(String name) { this.name = validateName(name); }

        public String getName() { return this.name; }

        public void setName(String name) { this.name = validateName(name); }

        public static String validateName(String name) {
            if (Objects.requireNonNull(name, "name can not be null").isEmpty()) {
                throw new IllegalArgumentException("name can not be empty");
            }
            return name;
        }
    }

    public static class Teilnehmer extends Person implements Comparable<Teilnehmer> {
        private int matrikelnummer;

        public Teilnehmer(String name, int matrikelnummer) {
            super(name);
            this.matrikelnummer = validateMatrikelnummer(matrikelnummer);
        }

        public int getMatrikelnummer() { return this.matrikelnummer; }

        public void setMatrikelnummer() { this.matrikelnummer = validateMatrikelnummer(matrikelnummer); }

        public static int validateMatrikelnummer(int matrikelnummer) {
            if (matrikelnummer < 1) {
                throw new IllegalArgumentException("matrikelnummer can not be smaller than 1");
            }
            return matrikelnummer;
        }

        @Override
        public String toString() {
            return String.format("%s (%d)", getName(), matrikelnummer);
        }

        @Override
        public int compareTo(Teilnehmer o) {
            return Comparator.comparing(Teilnehmer::getName).compare(this, o);
        }

    }

    public static class Uebungsgruppe {
        private Person tutor;
        private static final int MIN_TEILNEHMER = 3;
        private static final int MAX_TEILNEHMER = 30;
        private final Set<Teilnehmer> teilnehmer = new HashSet<>(MAX_TEILNEHMER);

        public Uebungsgruppe(Person tutor, Teilnehmer... teilnehmer) { this(tutor, Arrays.asList(teilnehmer)); }

        public Uebungsgruppe(Person tutor, Collection<Teilnehmer> teilnehmer) {
            this.tutor = tutor;
            setTeilnehmer(teilnehmer);
        }

        public void setTutor(Person tutor) { this.tutor = tutor; }

        public Person getTutor() { return this.tutor; }

        public void setTeilnehmer(Collection<Teilnehmer> teilnehmer) {
            if (teilnehmer.size() < MIN_TEILNEHMER || teilnehmer.size() > MAX_TEILNEHMER) {
                Objects.requireNonNull(teilnehmer, "teilnehmer can not be empty");
                throw new IllegalArgumentException("invalid amount of teilnehmer");
            }
            this.teilnehmer.clear();
            this.teilnehmer.addAll(teilnehmer);
        }

        public Set<Teilnehmer> getTeilnehmer() { return teilnehmer; }
    }

    public static class Vorlesung {
        private Person dozent;
        private String titel;
        private List<Uebungsgruppe> uebungsgruppe;

        public Vorlesung (Person dozent, String titel, Uebungsgruppe... uebungsgruppe) {
            this(dozent, titel, Arrays.asList(uebungsgruppe));
        }

        public Vorlesung (Person dozent, String titel, List<Uebungsgruppe> uebungsgruppe) {
            this.dozent = dozent;
            this.titel = validateTitel(titel);
            setUebungsgruppe(uebungsgruppe);
        }

        public void setDozent(Person dozent) { this.dozent = dozent; }

        public Person getDozent() { return this.dozent; }

        public void setTitel(String titel) { this.titel = validateTitel(titel); }

        public String getTitel() { return this.titel; }

        public static String validateTitel(String titel) {
            if (Objects.requireNonNull(titel, "titel can not be null").isEmpty()) {
                throw new IllegalArgumentException("titel can not be empty");
            }
            return titel;
        }

        public void setUebungsgruppe(List<Uebungsgruppe> uebungsgruppe) {
            if(!uebungsgruppe.isEmpty()) {
                this.uebungsgruppe = uebungsgruppe;
            } else {
                this.uebungsgruppe = new LinkedList<Uebungsgruppe>();
            }
        }

        public List<Teilnehmer> getTeilnehmer() {
            return this.uebungsgruppe.stream()
                    .map(Uebungsgruppe::getTeilnehmer)
                    .flatMap(Set::stream)
                    .sorted()
                    .collect(Collectors.toList());
        }
    }
}