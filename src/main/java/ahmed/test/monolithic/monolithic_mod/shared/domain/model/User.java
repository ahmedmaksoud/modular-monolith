package ahmed.test.monolithic.monolithic_mod.shared.domain.model;

public class User {

    private LANGS lang;

    public static enum LANGS {
        ARABIC, ENGLISH

    }
    public LANGS getLang() {
        return lang;
    }


}
