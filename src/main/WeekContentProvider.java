package main;

import java.util.HashMap;

public class WeekContentProvider {
    private static final String[] horoscopeList = {"aries", "taurus", "gemini", "cancer", "leo", "virgo", "libra", "scorpio", "sagittarius"
            , "capricorn", "aquarius", "pisces"};

    private static final String[] horoscopeWeekContent = new String[12];

    static {
        horoscopeWeekContent[0] = "You've got high hopes about everything this week, including your love life. Your mind simply won't go dark " +
                "about anything and because it's easy to see the bright side of everything, your romantic world should run relatively smoothly now. If " +
                "there are any problems, you'll have a solution without hesitation. You might also be in a situation where you feel more financially " +
                "supported by your partner than usual. There might be good news for your sweetheart about a raise, bonus, or investment that has paid off " +
                "nicely. You'll want to celebrate!";

        horoscopeWeekContent[1] = "You'll feel comforted this week by the fact that you and your partner share similar beliefs about " +
                "philosophy, spirituality, and overall ethics. What you love the most about your sweetheart now is the fact that he or she has an " +
                "unwavering sense of personal integrity. This uprightness or character is not only attractive to you, but it also makes you realize " +
                "that the person you're with truly is a catch. You don't need to dream about a stable relationship with someone. You're living the " +
                "dream.";

        horoscopeWeekContent[2] = "Conversations with your partner will be on point this week. Mercury, your ruling planet, will move " +
                "into your relationship sector on Wednesday, offering you an extraordinary level of support in communication with your love through " +
                "January 4. If you have any decisions to make together, you'll find it very easy to collaborate and compromise now. If you have a " +
                "personal decision to make, you'll find yourself using your significant other as a sounding board much more than usual. He or she will " +
                "be an indispensable source of wisdom for you now.";
        ;

        horoscopeWeekContent[3] = "If you're completely single, then it's possible you'll meet someone this week you're sure is " +
                "relationship material. This person will be mature, possibly even older than you. It's his or her responsible approach to life and to " +
                "partnership that will attract you more than anything else. In fact, you'll find it refreshing to meet someone who has zero interest in" +
                " playing any type of immature love games. Don't be surprised if this love connection takes time to get off the ground. Be patient -- " +
                "it's worth waiting for.";

        horoscopeWeekContent[4] = "If you're single, then it's possible you'll meet someone this week who is from a different background than " +
                "you are. This might be a different culture or race but can also be a different religion. Whatever the dissimilarities are, you'll find " +
                "that this is exactly what draws you to one another. Explore! If you're already in a relationship, then you and your sweetheart will be " +
                "expressing love to each other in abundance. Don't be surprised if love notes are exchanged or if you are gushing sentimental words at " +
                "each other left and right. How romantic!";

        horoscopeWeekContent[5] = "Although you're more serious than ever about matters of the heart, you aren't necessarily afraid to " +
                "love. You might have been in this situation recently and if so, there's a good chance you've done the inner work necessary to confront" +
                " this blockage. This week, you'll find it much easier to communicate to your sweetheart just how deep your feelings are without " +
                "allowing yourself to fall into any type of insecurity about opening your heart. It's safe for you to love, so love.";

        horoscopeWeekContent[6] = "If you and your mate live together, then an event may occur this week that fortifies your domestic " +
                "and financial stability. This can be anything from the two of you making a purchase together that involves real estate to working as " +
                "a team to ensure there's a greater sense of stability overall in your home life. Another possibility is that you have been dating " +
                "someone and decide that it's time to move in together. This is a decision neither of you has come too lightly. You have carefully " +
                "thought this through.";

        horoscopeWeekContent[7] = "It'll be easy for you to communicate your worth to others today, especially to your partner. However," +
                " you won't need to work very hard this week to prove how amazing you are. In fact, it seems quite obvious to everyone around you now " +
                "that Venus is moving through your sign. Your qualities of magnetism are even stronger than usual, and you've also got staying power " +
                "when it comes to your level of attractiveness. You're like a fine wine that is getting better with age.";

        horoscopeWeekContent[8] = "You might be quietly reassessing your worth when it comes to matters of the heart. If you've kept " +
                "yourself in a situation where you feel your partner truly doesn't \"see\" you and value you, then it's likely you're no longer willing" +
                " to remain in that type of scenario. As long as your person offers you plenty of respect and breathing room, you don't need much when" +
                " it comes to love. If you need to cut ties with someone who isn't meeting your must-haves in love, the good news is you're optimistic" +
                " about finding someone who will. You won't compromise -- and you shouldn't.";

        horoscopeWeekContent[9] = "If you're single, this is a week where you might find stable romantic potential through a friend. Trust " +
                "your pals to set you up on a date with a man or woman they think would be perfect for you. They won't steer you wrong! This person will " +
                "be mature, responsible, deep, and fully capable of intimacy. You'll be pleasantly surprised at how comfortable you feel with this person," +
                " almost as if you've met before. Guess what? You probably have in a past life!";

        horoscopeWeekContent[10] = "Your love life is directly tied into your social life this week. Mercury will move into your friendship sector on " +
                "Wednesday where he'll remain through January 4. Since Mercury rules your romance sector, it's very likely that your time with friends will" +
                " somehow help your love life situation. Maybe you need trusted guidance from your pals about how to proceed with your relationship or " +
                "someone you're attracted to. Your friends will give you honest perspective from a place that feeds your soul rather than your ego. In " +
                "other words, you'll hear what you need to, even if it is not what you want to.";

        horoscopeWeekContent[11] = "Your partner might be focused much more on career and his or her reputation this week than your " +
                "relationship. Fortunately, you won't take this personally. In fact, you've got plenty of your own professional opportunities to take " +
                "advantage of, so it might be nice for both of you to have a week off from any romantic pressure and focus on other matters. You'll be " +
                "fully supportive of each other as you pursue your goals, which is always a wonderful sign. Your love life coasts this week, but your " +
                "careers will soar!";

    }

    public static String getWeekByHoroscope(int horoscopeIndex) {
        return horoscopeWeekContent[horoscopeIndex];
    }
}
