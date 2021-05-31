import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@ScriptManifest(name = "Chal Chat Scraper", author = "OP", version = 1.0, info = "", logo = "")

public class ClanChatPlayers extends Script
{
    public String status = "";
    public String currentCC = "";
    public String content = "";

    public long startTime;
    public long timeElapsed;

    public HashMap<String, String> clanWebhooks = new HashMap<>();
    public CustomBreakManager customBreakManager;

    public void onStart()
    {
        startTime = System.currentTimeMillis();

        customBreakManager = new CustomBreakManager();
        customBreakManager.exchangeContext(getBot());

        getBot().getRandomExecutor().overrideOSBotRandom(customBreakManager);

        clanWebhooks.put("clanapex", "https://discord.com/api/webhooks/826439743192956968/4RgQndhrYYbUdyNjNUI7Pglwfu0TO8e8gmXeVHDg6KV3NsMj-nJ0owzfwMEcxXXFcdMR");
        clanWebhooks.put("bp pub", "https://discord.com/api/webhooks/826439924794261504/B2Sk08rcGRrFR2zPxjAjb5rvDUxQ_i7U1BTSO2ZxATOHa2VxKzCiAWCvErHI-Y9QVuNl");
        clanWebhooks.put("fatality", "https://discord.com/api/webhooks/826440067990945863/LRx0cz4ptCKD5dODA4yp3hAP7EGUxhy74rLo9m41hfD4kuP1T7amdixF4Y_jwTzLvFq6");
        clanWebhooks.put("foe public", "https://discord.com/api/webhooks/826440199231111208/VBRCzK2_tKDaHynGdJ4ikB5TYrUXbsCOqw2wkKiX1JMWEApLiJQtdcPrSEjOG8CbcJ5N");
        clanWebhooks.put("cc hydra", "https://discord.com/api/webhooks/826440591394340865/DXTe-MND9Eabj896Dzc0vAPPXG9TfhvAeoI0u_TBbQB6EK_OBV5L3MixI-GiHMFEcNHo");
        clanWebhooks.put("js cc", "https://discord.com/api/webhooks/826440738006368306/R1-W7RML7YPGeMrvG-OcrR_U3yP0BQFoGjZigb-RRC0l2nMePD-6_a4U9oiAvlwADZP4");
        clanWebhooks.put("ly rs", "https://discord.com/api/webhooks/826440929950957598/PTpQEUzz6_xHQST9I3XQMk9iTrpOr0l_h6qrx0-cHcCPJK0rS7fVf2RQzS9kSO7xQWVL");
        clanWebhooks.put("og pub", "https://discord.com/api/webhooks/826452248817172511/y-a07YldrIM6-FHnGo07ij6KS38Edi2Ibon618L-JM545VnbNkbPgRB2QCVGSqvrW9a7");
        clanWebhooks.put("clan rage", "https://discord.com/api/webhooks/826452352415563786/dy2gBsgUh4TyHWYV3YNQ2s_VZ06K2ZVZHvILyt0pTR25Nk9_iDLIn_CwjrrjbsDyzaj7");
        clanWebhooks.put("sf rs", "https://discord.com/api/webhooks/826452752845766706/Xaps51E8gZE4zYjUNT1PZ57RpanwNW86GQ6ma7gW7LtR86OCrU88e8GcQ7WSTGot7kB_");
        clanWebhooks.put("supremacy rs", "https://discord.com/api/webhooks/826452825928237087/6KA11GEu22G_1DG2h1rpdec7Vc0oh5-oxYSDO5FwwFD_tQWrhr4o1ZNHcE_BmamUS0Uw");
        clanWebhooks.put("wolves den", "https://discord.com/api/webhooks/826452916688388136/6e2aVXgP6P2NYH8hv4O9ZeUqauW9fEsv39S3wOHubY1Wn_HHNXpugO-e4Jw4jy1ZHqAT");
        clanWebhooks.put("venom pub", "https://discord.com/api/webhooks/826452986737721404/-AkWBbsiE1f7HJJIhoDlZj9lk0Z4Mt1e_6ltU73hFD1TpODIqvTJ2OQglUVQN3Apaoti");
        clanWebhooks.put("rs zenith", "https://discord.com/api/webhooks/826453070791049276/dWWSyMFTCVAVhYxMc1QmN9h3eb-7Uh9WavfeHJoKheWGtUXiWDUUy-xh-jsbwL5re42M");
        clanWebhooks.put("teamph", "https://discord.com/api/webhooks/848565403173257286/bJY93Rc-ztoeiSyoE1zxJh5IUQE_PD3wZzRh8gBPPh8keXwODNndgI3Yf0Bu2mXoU6mR");
    }

    public int onLoop() throws InterruptedException
    {
        if ( myPlayer().isVisible() )
        {
            if ( !getTabs().isOpen(Tab.CLANCHAT) )
            {
                getTabs().open(Tab.CLANCHAT);
                sleep(500,1500);
            }

            for ( String clan : clanWebhooks.keySet() )
            {

                status = "Joining and Scraping " + clan.toUpperCase() + " Clan Chat";
                leaveClanChat();
                sleep(500,1500);
                if ( notInClanChat() )
                {
                    joinClanChat(clan);
                    currentCC = clan;

                    // array of visible members  in the clan chat
                    RS2Widget[] players = getWidgets().get(7,12).getChildWidgets();

                    if (players != null)
                    {
                        for (RS2Widget player : players)
                        {
                            //hacky but posts nicely formatted to webhook
                            content += player.getMessage();
                            content += "\\n";
                        }
                        try
                        {
                            discordWebHook(clanWebhooks.get(currentCC), content);
                        }
                        catch (IOException e)
                        {
                           log(e);
                        }
                        content = "";
                    }
                }
            }
            status = "Waiting two hours to scrape Clan Chats again";
            customBreakManager.startBreaking(TimeUnit.MINUTES.toMillis(120), true);
        }
        return random(5000,13000);
    }

    public void discordWebHook(String hookUrl, String content ) throws IOException
    {
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

    public void joinClanChat(String name)
    {

        RS2Widget clanChat = getWidgets().get(548, 41);
        RS2Widget enterName = getWidgets().get(162, 32);
        RS2Widget joinChat = getWidgets().get(7, 17);
        clickOnWidget(clanChat);
        sleep(2000,3000);
        clickOnWidget(joinChat);

        if (enterName != null && enterName.isVisible())
        {
            getKeyboard().typeString(name);
            sleep(4000, 5000);
        }
        else if (joinChat != null && joinChat.isVisible()) clickOnWidget(joinChat);
    }

    public void leaveClanChat()
    {
        RS2Widget leaveWidget = getWidgets().getWidgetContainingText("Leave");
        if (leaveWidget != null && leaveWidget.isVisible()) clickOnWidget(leaveWidget);
    }

    public boolean notInClanChat()
    {
        RS2Widget notInChat = getWidgets().getWidgetContainingText("Not in channel");
        return (notInChat != null && notInChat.isVisible());
    }

    private void clickOnWidget(RS2Widget widget)
    {
        widget.hover();
        getMouse().click(false);
        sleep(1500,3000);
    }

    private void sleep(int min, int max)
    {
        try
        {
            sleep(new Random().nextInt(max-min) + min);
        } catch(Exception e)
        {
            log(e);
        }
    }

    public void onPaint(final Graphics2D g)
    {

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

