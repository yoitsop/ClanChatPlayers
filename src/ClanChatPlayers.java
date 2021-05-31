import java.awt.*;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.json.JSONObject;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;


@ScriptManifest(name = "Chal Chat Scraper", author = "OP", version = 1.0, info = "", logo = "")

public class ClanChatPlayers extends Script
{

    private ClanChatPlayersGui ccGui = new ClanChatPlayersGui();


    private String status = "";
    public long startTime;
    public long timeElapsed;
    ArrayList<String> ccMembers = new ArrayList<String>();
    String currentCC = "";
    String content = "";
    String testing1 = "https://discord.com/api/webhooks/826461119282544670/ONrO5ZGFuPXEJlXkSqVhASwlqF3cDDHNH16cuz_5haesHyrmXhf_PObNpfncXZWAaSow";
    String testing2 = "https://discord.com/api/webhooks/826461303232921630/vi9NtnJZzQmEzxwFBB0ZYo8OD9ylPElCT-8mBktDdPyVJde8OlHOBDHgdR1dOnhH9p5Y";
    String apexUrl = "https://discord.com/api/webhooks/826439743192956968/4RgQndhrYYbUdyNjNUI7Pglwfu0TO8e8gmXeVHDg6KV3NsMj-nJ0owzfwMEcxXXFcdMR";
    String bpUrl = "https://discord.com/api/webhooks/826439924794261504/B2Sk08rcGRrFR2zPxjAjb5rvDUxQ_i7U1BTSO2ZxATOHa2VxKzCiAWCvErHI-Y9QVuNl";
    String fiUrl = "https://discord.com/api/webhooks/826440067990945863/LRx0cz4ptCKD5dODA4yp3hAP7EGUxhy74rLo9m41hfD4kuP1T7amdixF4Y_jwTzLvFq6";
    String foeUrl = "https://discord.com/api/webhooks/826440199231111208/VBRCzK2_tKDaHynGdJ4ikB5TYrUXbsCOqw2wkKiX1JMWEApLiJQtdcPrSEjOG8CbcJ5N";
    String hydraUrl = "https://discord.com/api/webhooks/826440591394340865/DXTe-MND9Eabj896Dzc0vAPPXG9TfhvAeoI0u_TBbQB6EK_OBV5L3MixI-GiHMFEcNHo";
    String jsUrl = "https://discord.com/api/webhooks/826440738006368306/R1-W7RML7YPGeMrvG-OcrR_U3yP0BQFoGjZigb-RRC0l2nMePD-6_a4U9oiAvlwADZP4";
    String lyUrl = "https://discord.com/api/webhooks/826440929950957598/PTpQEUzz6_xHQST9I3XQMk9iTrpOr0l_h6qrx0-cHcCPJK0rS7fVf2RQzS9kSO7xQWVL";
    String legionUrl = "https://discord.com/api/webhooks/826441118748639273/R6sP5XtItzYvGOA9zCW_4JJV2sTbKIMkoWGNy6h2nu9sjYz1Kknv4G-Yl_H2OxK2GOfb";
    String ogUrl = "https://discord.com/api/webhooks/826452248817172511/y-a07YldrIM6-FHnGo07ij6KS38Edi2Ibon618L-JM545VnbNkbPgRB2QCVGSqvrW9a7";
    String rageUrl = "https://discord.com/api/webhooks/826452352415563786/dy2gBsgUh4TyHWYV3YNQ2s_VZ06K2ZVZHvILyt0pTR25Nk9_iDLIn_CwjrrjbsDyzaj7";
    String sfUrl = "https://discord.com/api/webhooks/826452752845766706/Xaps51E8gZE4zYjUNT1PZ57RpanwNW86GQ6ma7gW7LtR86OCrU88e8GcQ7WSTGot7kB_";
    String supUrl = "https://discord.com/api/webhooks/826452825928237087/6KA11GEu22G_1DG2h1rpdec7Vc0oh5-oxYSDO5FwwFD_tQWrhr4o1ZNHcE_BmamUS0Uw";
    String trUrl = "https://discord.com/api/webhooks/826452916688388136/6e2aVXgP6P2NYH8hv4O9ZeUqauW9fEsv39S3wOHubY1Wn_HHNXpugO-e4Jw4jy1ZHqAT";
    String venUrl = "https://discord.com/api/webhooks/826452986737721404/-AkWBbsiE1f7HJJIhoDlZj9lk0Z4Mt1e_6ltU73hFD1TpODIqvTJ2OQglUVQN3Apaoti";
    String zenUrl = "https://discord.com/api/webhooks/826453070791049276/dWWSyMFTCVAVhYxMc1QmN9h3eb-7Uh9WavfeHJoKheWGtUXiWDUUy-xh-jsbwL5re42M";
    String frUrl = "https://discord.com/api/webhooks/826453158929891388/jcNFkFrPu4b_epS1amjZQQM4uh_C5fQuTX9WZkdcHhPJQ5fB4P6ePtDfvlXX7nUSwy5z";
    String GeneralUrl = "https://discord.com/api/webhooks/826458345636102164/WLWlU94e5Dgy4U13GcDgLVSU0De6r3hu6sqn6gmOoCFmtNCOrXJQTBjTXExzvrsVyiDz";
    public void onStart() {
        status = "Starting up..";
        startTime = System.currentTimeMillis();
        if (ccGui == null){
            ccGui = new ClanChatPlayersGui();
        }
    }

    public int onLoop() throws InterruptedException {

        ArrayList<String> clanChats = ccGui.getStringlist();
        status = "Waiting for CC Entries in GUI";

        if ( myPlayer().isVisible() && ccGui.isBotStarted )
        {
            for (int i = 0; i < clanChats.size(); i++)
            {
                status = "Joining " + clanChats.get(i).toUpperCase() + " Clan Chat";
                getTabs().open((Tab.CLANCHAT));
                leaveClanChat();

                if (notInClanChat())
                {
                    log("posting");
                    joinClanChat(clanChats.get(i));
                    currentCC = clanChats.get(i).toLowerCase();

                    RS2Widget[] players = getWidgets().get(7,16).getChildWidgets();
                    if (players != null)

                    {
                        for (RS2Widget player : players)
                        {
                            //hacky but cba to fuck with the webhook class to work with arrays
                            content += player.getMessage();
                            content += "\\n";
                        }
                        log(currentCC);
                        try {
                            if (currentCC.equals("ClanApex".toLowerCase())){
                                discordWebHook(testing1,content);
                            }
                            else if (currentCC.equals("BP Pub".toLowerCase())){
                                discordWebHook(testing2, content);
                            }/*
                            if (currentCC.equals("ClanApex".toLowerCase())){
                                discordWebHook(apexUrl,content);
                            }
                            else if (currentCC.equals("BP Pub".toLowerCase())){
                                discordWebHook(bpUrl, content);
                            }
                            else if (currentCC.equals("Fatality".toLowerCase())){
                                discordWebHook(fiUrl, content);
                            }
                            else if (currentCC.equals("Foe Public".toLowerCase())){
                                discordWebHook(foeUrl, content);
                            }
                            else if (currentCC.equals("CC Hydra".toLowerCase())){
                                discordWebHook(hydraUrl, content);
                            }
                            else if (currentCC.equals("JS cc".toLowerCase())){
                                discordWebHook(jsUrl, content);
                            }
                            else if (currentCC.equals("Ly Rs".toLowerCase())){
                                discordWebHook(lyUrl, content);
                            }
                            else if (currentCC.equals("Pure Legion".toLowerCase())){
                                discordWebHook(legionUrl, content);
                            }
                            else if (currentCC.equals("OG pub".toLowerCase())){
                                discordWebHook(ogUrl, content);
                            }
                            else if (currentCC.equals("Clan Rage".toLowerCase())){
                                discordWebHook(rageUrl, content);
                            }
                            else if (currentCC.equals("SF Rs".toLowerCase())){
                                discordWebHook(sfUrl, content);
                            }
                            else if (currentCC.equals("Supremacy RS".toLowerCase())){
                                discordWebHook(supUrl, content);
                            }
                            else if (currentCC.equals("Wolves Den".toLowerCase())){
                                discordWebHook(trUrl, content);
                            }
                            else if (currentCC.equals("Venom Pub".toLowerCase())){
                                discordWebHook(venUrl, content);
                            }
                            else if (currentCC.equals("RS Zenith".toLowerCase())){
                                discordWebHook(zenUrl, content);
                            }
                            else if (currentCC.equals("Fury latins".toLowerCase())){
                                discordWebHook(frUrl, content);
                            }
                            else{
                                discordWebHook(GeneralUrl,content);
                            }*/
                        } catch (IOException e) {
                           log(e);
                        }
                        content = "";
                    }
                }
            }
            status = "Waiting 30 minutes to scrape Clan Chats again";
            log("end");
            sleep(7200000, 7200500);
        }
       // return random(1800000, 1800050);
        return random(5000,13000);

    }

    public void discordWebHook(String hookUrl, String content ) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now) + " EST";
        DiscordWebhook webhook = new DiscordWebhook(hookUrl);
        webhook.setAvatarUrl("https://i.imgur.com/1Ls5Pqo.png");
        webhook.setUsername("Legacy Scraper");
        webhook.setTts(true);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle("Scraped from: " + currentCC.toUpperCase() + " CC")
                .setDescription(date)
                .setColor(Color.BLUE)
                .addField("Scraped Usernames and World", content, true)
                .setImage("https://i.imgur.com/SHw7XYG.png")
                .setThumbnail("https://i.imgur.com/1Ls5Pqo.png")
                .setAuthor("Legacy Scraper", "https://github.com/yoitsop", "https://i.imgur.com/Nld2NLq.png")
                .setUrl("https://github.com/yoitsop"));
        webhook.execute(); //Handle exception
    }

    public boolean isClanChatTabOpen(){
        return Tab.CLANCHAT.isOpen(getBot());
    }

    public void joinClanChat(String name){

        //RS2Widget enterName = getWidgets().getWidgetContainingText("Enter the player name whose channel you wish to join:");
        RS2Widget enterName = getWidgets().get(162, 32);
        RS2Widget joinChat = getWidgets().getWidgetContainingText("Join Chat");
        clickOnWidget(joinChat);

        if (enterName != null && enterName.isVisible()){
            getKeyboard().typeString(name);
            sleep(4000, 5000);
        }
        else if (joinChat != null && joinChat.isVisible()) clickOnWidget(joinChat);
    }

    public void leaveClanChat(){
        RS2Widget leaveWidget = getWidgets().getWidgetContainingText("Leave Chat");
        if (leaveWidget != null && leaveWidget.isVisible()) clickOnWidget(leaveWidget);
    }

    public boolean notInClanChat(){
        RS2Widget notInChat = getWidgets().getWidgetContainingText("Not in chat");
        return (notInChat != null && notInChat.isVisible());
    }

    private void clickOnWidget(RS2Widget widget){
        widget.hover();
        getMouse().click(false);
        sleep(1500,3000);
    }

    private void sleep(int min, int max){

        try{
            sleep(new Random().nextInt(max-min) + min);
        } catch(Exception e){
            log(e);
        }
    }

    public void onPaint(final Graphics2D g) {

        timeElapsed = System.currentTimeMillis() - startTime;

        long second = (timeElapsed / 1000) % 60;
        long minute = (timeElapsed / (1000 * 60)) % 60;
        long hour = (timeElapsed / (1000 * 60 * 60)) % 24;

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(new Color(255, 255,255));
        g.drawString("Status: " + status, 5,335);
        g.drawString("Uptime: " + hour +":"+ minute + ":" + second, 5, 315);
    }
}

