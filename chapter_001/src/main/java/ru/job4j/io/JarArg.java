package ru.job4j.io;

public class JarArg {
    private String outputFile;
    private String directory;
    private String nameFile;
    private SearchFiles searcher;
    private final String[] args;

    public JarArg(String[] args) {
        this.args = args;
    }

    private void valid() {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder and expansion are null. Please set them in Edit Configuration -> Program agruments");
        }
    }
    public void checkArgs() {
        valid();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d":
                    directory = args[i + 1];
                    break;
                case "-n":
                    nameFile = args[i + 1];
                    break;
                case "-m":
                    findSearcher("-m");
                    break;
                case "-f":
                    findSearcher("-f");
                    break;
                case "-r":
                    findSearcher("-r");
                    break;
                case "-o":
                    outputFile = args[i + 1];
                    break;
                default:
            }
        }
    }

    private void findSearcher(String atr) {
        if (atr.equals("-m")) {
            searcher = new SearchFiles(path -> path.toFile().getName().endsWith(nameFile.split("\\.")[1]));
        } else if (atr.equals("-f")) {
            searcher = new SearchFiles(path -> path.toFile().getName().contains(nameFile));
        } else if (atr.equals("-r")) {
            searcher = new SearchFiles(path -> path.toFile().getName().matches(nameFile));
        }
    }

    public String getDirectory() {
        return directory;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public SearchFiles getSearcher() {
        return searcher;
    }

}

