import java.io.PrintStream;

public class Document {
    private int[][] chaine;
    private int nbCarnets;
    private int nbPages;
    private int feuillesParCarnet;
    private int pagesParCarnet;
    private int blankPage;
    private int pagesMoinsDernierCarnet;
    private int pagesRestantes;
    private int carnetTotal;
    private int nbFeuilles;
    private int carnetFeuillesRestant;
    private int vraiPagesTotal;
    private int carnetRestant;
    private int vraiFeuillesTotal;

    public Document(int nbPages, int feuillesParCarnet)
    {
        this.nbPages = nbPages;
        this.feuillesParCarnet = feuillesParCarnet;
        this.carnetTotal = 0;


        this.pagesParCarnet = (feuillesParCarnet * 4);
        this.nbCarnets = (nbPages / this.pagesParCarnet);
        this.blankPage = (nbPages + 1);


        this.pagesMoinsDernierCarnet = (this.nbCarnets * this.pagesParCarnet);
        this.pagesRestantes = (nbPages - this.pagesMoinsDernierCarnet);

        verifieDernierCarnet();

        this.vraiPagesTotal = (this.pagesMoinsDernierCarnet + this.carnetRestant);
        this.carnetFeuillesRestant = (this.carnetRestant / 4);
        this.vraiFeuillesTotal = (this.vraiPagesTotal / 4);

        this.nbFeuilles = (this.pagesMoinsDernierCarnet / 4);
    }







    public void displayDebugConsole()
    {
        System.out.println("- Nombre de pages : " + this.nbPages);
        System.out.println("- Numéro de la page blanche : " + this.blankPage);
        System.out.println("- Nombre de carnets total: " + this.nbCarnets);
        System.out.println("- Nombre de feuilles par carnet :" + this.feuillesParCarnet);
        System.out.println("- Nombre de pages par carnet : " + this.pagesParCarnet);
        System.out.println("- Nombre de page sans le dernier carnet: " + this.pagesMoinsDernierCarnet);
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
        if (this.carnetRestant == 0) {
            System.out.println("Le compte est juste : pas de carnet restant");
        } else {
            System.out.println("- Nombre de feuilles dernier carnet : " + this.carnetFeuillesRestant);
            System.out.println("- Nombre de pages dernier carnet : " + this.carnetRestant);
            System.out.println("- Nombre de pages total : " + this.vraiPagesTotal);
        }

        System.out.println("- Nombre de feuilles total : " + this.vraiFeuillesTotal);
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
    }







    public void displayCarnetsConsole()
    {
        for (int i = 0; i < this.chaine.length; i++) {
            System.out.println("Feuille numéro : " + (i + 1) + " ");
            for (int j = 0; j < this.chaine[i].length; j++) {
                System.out.print(this.blankPage + ", ");
            }



            System.out.println("|");
        }
    }






    public void create()
    {
        this.chaine = new int[this.nbFeuilles + this.carnetFeuillesRestant][4];



        int feuilles = 0;

        int count = 0;
        int countCarnets = 0;
        for (int carnets = 0; carnets < this.nbCarnets; carnets++) {
            int feuillesParCarnetComparator = this.feuillesParCarnet + feuilles;
            while (feuilles < feuillesParCarnetComparator)
            {
                this.chaine[feuilles][0] = (this.pagesParCarnet - 2 * count + this.pagesParCarnet * carnets);
                this.chaine[feuilles][1] = (1 + 2 * count + this.pagesParCarnet * carnets);
                this.chaine[feuilles][2] = (2 + 2 * count + this.pagesParCarnet * carnets);
                this.chaine[feuilles][3] = (this.pagesParCarnet - 2 * count + this.pagesParCarnet * carnets - 1);
                feuilles++;
                count++;
            }
            count = 0;
            countCarnets = carnets;
        }


        int feuillesParCarnetComparator = this.carnetFeuillesRestant + feuilles;
        while (feuilles < feuillesParCarnetComparator)
        {
            this.chaine[feuilles][0] = (this.carnetRestant - 2 * count + this.pagesParCarnet * (countCarnets + 1));
            this.chaine[feuilles][1] = (1 + 2 * count + this.pagesParCarnet * (countCarnets + 1));
            this.chaine[feuilles][2] = (2 + 2 * count + this.pagesParCarnet * (countCarnets + 1));
            this.chaine[feuilles][3] = (this.carnetRestant - 2 * count + this.pagesParCarnet * (countCarnets + 1) - 1);
            feuilles++;
            count++;
        }
    }




    public void verifieDernierCarnet()
    {
        this.carnetRestant = 0;

        if (this.nbPages % this.pagesMoinsDernierCarnet != 0) {
            boolean multipleOk = false;
            this.carnetTotal += 1;
            while (!multipleOk) {
                if (this.carnetRestant >= this.pagesRestantes) {
                    multipleOk = true;
                } else {
                    this.carnetRestant += 4;
                }
            }
        }
    }

    public String stringParser(String separator, boolean parCarnet) {
        String s = "";
        int carnet = 0;
        int numeroCarnet = 1;
        if (parCarnet) {
            s = s + "Carnet Numero " + numeroCarnet + "-----------------------";
            s = s + "\r\n";
        }
        for (int i = 0; i < this.chaine.length; i++) {
            if (parCarnet)
            {
                if (carnet < this.feuillesParCarnet) {
                    carnet++;
                }
                else {
                    numeroCarnet++;
                    carnet = 1;
                    s = s + "\r\n";
                    s = s + "Carnet Numero " + numeroCarnet + "-----------------------";
                    s = s + "\r\n";
                }
            }


            for (int j = 0; j < this.chaine[i].length; j++) {
                s = s + (this.chaine[i][j] <= this.nbPages ? this.chaine[i][j] + separator + " " : new StringBuilder().append(this.blankPage).append(separator).append(" ").toString());
            }
        }

        return s;
    }





    public int[][] getChaine()
    {
        return this.chaine;
    }

    public void setChaine(int[][] chaine) {
        this.chaine = chaine;
    }

    public int getNbCarnets() {
        return this.nbCarnets;
    }

    public void setNbCarnets(int nbCarnets) {
        this.nbCarnets = nbCarnets;
    }

    public int getNbPages() {
        return this.nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public int getFeuillesParCarnet() {
        return this.feuillesParCarnet;
    }

    public void setFeuillesParCarnet(int feuillesParCarnet) {
        this.feuillesParCarnet = feuillesParCarnet;
    }

    public int getPagesParCarnet() {
        return this.pagesParCarnet;
    }

    public void setPagesParCarnet(int pagesParCarnet) {
        this.pagesParCarnet = pagesParCarnet;
    }

    public int getBlankPage() {
        return this.blankPage;
    }

    public void setBlankPage(int blankPage) {
        this.blankPage = blankPage;
    }

    public int getPagesMoinsDernierCarnet() {
        return this.pagesMoinsDernierCarnet;
    }

    public void setPagesMoinsDernierCarnet(int pagesMoinsDernierCarnet) {
        this.pagesMoinsDernierCarnet = pagesMoinsDernierCarnet;
    }

    public int getPagesRestantes() {
        return this.pagesRestantes;
    }

    public void setPagesRestantes(int pagesRestantes) {
        this.pagesRestantes = pagesRestantes;
    }

    public int getCarnetTotal() {
        return this.carnetTotal;
    }

    public void setCarnetTotal(int carnetTotal) {
        this.carnetTotal = carnetTotal;
    }

    public int getNbFeuilles() {
        return this.nbFeuilles;
    }

    public void setNbFeuilles(int nbFeuilles) {
        this.nbFeuilles = nbFeuilles;
    }

    public int getCarnetFeuillesRestant() {
        return this.carnetFeuillesRestant;
    }

    public void setCarnetFeuillesRestant(int carnetFeuillesRestant) {
        this.carnetFeuillesRestant = carnetFeuillesRestant;
    }

    public int getVraiPagesTotal() {
        return this.vraiPagesTotal;
    }

    public void setVraiPagesTotal(int vraiPagesTotal) {
        this.vraiPagesTotal = vraiPagesTotal;
    }

    public int getCarnetRestant() {
        return this.carnetRestant;
    }

    public void setCarnetRestant(int carnetRestant) {
        this.carnetRestant = carnetRestant;
    }

    public int getVraiFeuillesTotal() {
        return this.vraiFeuillesTotal;
    }

    public void setVraiFeuillesTotal(int vraiFeuillesTotal) {
        this.vraiFeuillesTotal = vraiFeuillesTotal;
    }
}
