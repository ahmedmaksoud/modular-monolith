package ahmed.test.monolithic.monolithic_mod.shared.domain.model;

public class User {

    private LANGS lang;

    public  enum LANGS {
        ARABIC(1), ENGLISH(2);
        private final int code;

        LANGS(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

    }
    public LANGS getLang() {
        return lang;
    }


}
