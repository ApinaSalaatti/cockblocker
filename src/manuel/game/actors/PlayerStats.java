package manuel.game.actors;

/**
 *
 * @author Merioksan Mikko
 */
public class PlayerStats {
    public static final String DISEASE_HEALTHY = "healthy";
    public static final String DISEASE_AIDS = "aids";
    public static final String DISEASE_GONORRHEA = "gonorrhea";
    public static final String DISEASE_CHLAMYDIA = "chlamydia";
    
    Player player;
    
    /**
     * Very cool and informative stats
     */
    private long lifetime;
    private int score;
    private int killed;
    private int children;
    private float newChildPrcnt;
    private int enemiesThrough;
    private float aidsPrcnt, gonorrheaPrcnt, chlamydiaPrcnt;
    private boolean aids, gonorrhea, chlamydia;
    private boolean dead;
    
    private float mental, physical;
    
    private String eventLog;
    
    public PlayerStats(Player p) {
        player = p;
        
        lifetime = 0;
        score = 0;
        killed = 0;
        children = 0;
        newChildPrcnt = 0;
        enemiesThrough = 0;
        aidsPrcnt = gonorrheaPrcnt = chlamydiaPrcnt = 0;
        aids = gonorrhea = chlamydia = false;
        dead = false;
        
        mental = physical = 1;
        
        eventLog = "";
    }
    
    public void update(long deltaMs) {
        if(!dead) {
            lifetime += deltaMs;

            float oldPhys = physical;
            if(Math.random() < chlamydiaPrcnt && !chlamydia) {
                chlamydia = true;
                addEvent("You contracted chlamydia");
                player.getGame().getMessaging().addMessage("Uh oh!", "You have contracted chlamydia!");
            }
            if(Math.random() < gonorrheaPrcnt && !gonorrhea) {
                gonorrhea = true;
                addEvent("You contracted gonorrhea");
                player.getGame().getMessaging().addMessage("Oh dear!", "You have contracted gonorrhea!");
            }
            if(Math.random() < aidsPrcnt && !aids) {
                aids = true;
                addEvent("You contracted AIDS");
                player.getGame().getMessaging().addMessage("This is bad!", "You have contracted AIDS!");
            }

            if(aids) {
                physical -= 0.00001 * deltaMs;
                mental -= 0.00001 * deltaMs;
            }
            if(gonorrhea) {
                physical -= 0.000005 * deltaMs;
                mental -= 0.00001 * deltaMs;
            }
            if(chlamydia) {
                physical -= 0.000001 * deltaMs;
                mental -= 0.00001 * deltaMs;
            }

            if(mental < 0.5) {
                physical -= 0.00001 * deltaMs;
            }

            mental -= 0.00001 * children;

            if(oldPhys > 0.5 && physical < 0.5) {
                addEvent("Your health started to deteriorate");
                player.getGame().getMessaging().addMessage("Dear me!", "Your physical health is starting to get weak.");
            }

            if(physical <= 0) {
                addEvent("You died from all your illnesses. Sorry.");
                dead = true;
            }
            else if(lifetime >= 100000) {
                addEvent("You died of old age! Congratulations!");
                dead = true;
            }
        }
    }
    
    public void addEvent(String event) {
        long age = 15 + lifetime / 1000;
        eventLog += "Age " + age + ": " + event + ";";
    }
    public String getEventLog() {
        return eventLog;
    }
    
    public int getScore() {
        return score;
    }
    public void addScore(int s) {
        score += s;
    }
    public int getKilled() {
        return killed;
    }
    public void addKill() {
        killed++;
    }
    public int getChildren() {
        return children;
    }
    public void addChildChance() {
        newChildPrcnt += 0.2;
        if(newChildPrcnt >= 1.0) {
            children++;
            newChildPrcnt = 0;
            addEvent("Your " + children + ". child was born");
            player.getGame().getMessaging().addMessage("Whoops!", "You gave birth to your " + children + ". child!");
        }
    }
    
    public float getMental() {
        return mental;
    }
    public float getPhysical() {
        return physical;
    }
    
    public boolean hasDisease(String disease) {
        if(disease.equals(DISEASE_GONORRHEA)) {
            return gonorrhea;
        }
        else if(disease.equals(DISEASE_AIDS)) {
            return aids;
        }
        else if(disease.equals(DISEASE_CHLAMYDIA)) {
            return chlamydia;
        }
        
        return false;
    }
    public float getDisease(String disease) {
        if(disease.equals(DISEASE_GONORRHEA)) {
            return gonorrheaPrcnt;
        }
        else if(disease.equals(DISEASE_AIDS)) {
            return aidsPrcnt;
        }
        else if(disease.equals(DISEASE_CHLAMYDIA)) {
            return chlamydiaPrcnt;
        }
        
        return 0;
    }
    public void addDisease(String disease) {
        if(disease.equals(DISEASE_GONORRHEA)) {
            gonorrheaPrcnt += 0.1;
        }
        else if(disease.equals(DISEASE_AIDS)) {
            aidsPrcnt+= 0.1;
        }
        else if(disease.equals(DISEASE_CHLAMYDIA)) {
            chlamydiaPrcnt+= 0.1;
        }
    }
    
    public void enemyKilled(Enemy e) {
        addKill();
        addScore(e.getScoreValue());
    }
    
    public void enemyThrough(Enemy e) {
        enemiesThrough++;
        addDisease(e.getDisease());
        addChildChance();
    }
    
    public int getEnemiesThrough() {
        return enemiesThrough;
    }
    
    public void applyAntibiotics() {
        
    }
}
