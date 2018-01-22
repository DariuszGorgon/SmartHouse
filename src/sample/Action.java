package sample;

public class Action {

    public String name1;
    public float value1;
    public String threshold1;
    public String Setting1;

    Action (String name, float value, String threshold, String setting) {
        switch (name) {
            case "Temp. 1":
                if (threshold.matches("Próg górny")) {
                    name1 = "temp_1_threshold_high\n";
                    threshold1 = "temp_1_threshold_high_action\n";
                } else {
                    name1 = "temp_1_threshold_low\n";
                    threshold1 = "temp_1_threshold_low_action\n";
                }
                break;
            case "Temp. 2":
                if (threshold.matches("Próg górny")) {
                    name1 = "temp_2_threshold_high\n";
                    threshold1 = "temp_2_threshold_high_action\n";
                } else {
                    name1 = "temp_2_threshold_low\n";
                    threshold1 = "temp_2_threshold_low_action\n";
                }
                break;
        }
        switch (setting) {
            case "Włącz Klimatyzację":
                Setting1 = "1";
                break;
            case "Wyłącz Klimatyzację":
                Setting1 = "2";
                break;
            case "Otwórz okno":
                Setting1 = "3";
                break;
            case "Zamknij okno":
                Setting1 = "4";
                break;
        }
        value1 = value;
        //System.out.println(name1+"="+value+"\n"+threshold1+"="+Setting1);
    }

    public void setName(String name) {
        this.name1 = name;
    }

    public void setValue(int value) {
        this.value1 = value;
    }

    public void setThreshold(String threshold) {
        this.threshold1 = threshold;
    }

    public void setSetting(String setting) {
        Setting1 = setting;
    }

    public String getName() {
        return name1;
    }

    public float getValue() {
        return value1;
    }

    public String getThreshold() {
        return threshold1;
    }

    public String getSetting() {
        return Setting1;
    }

}

