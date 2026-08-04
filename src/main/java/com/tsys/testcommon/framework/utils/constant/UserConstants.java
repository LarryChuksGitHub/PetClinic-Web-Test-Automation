package com.tsys.testcommon.framework.utils.constant;

import static com.tsys.testcommon.framework.utils.string.StringUtil.APOSTROPHE;

public class UserConstants {

    public static final String UNICODE_LETTER = "\u00C3";
    public static final String UNICODE_LETTER2 = "\u00C6";
    //special characters allowed for first and last name
    public static final String SPECIAL_VALID_CHARACTERS = "ÀÁÂÃÄÅÆÇĈŘÈÉÊËÌÍÎÏÐÑÒÓÔÕŐÖØÙÚÛŰÜÝÞßẞàáâãäåæçĉřèéêëìíîïðñòóôõőöøùúûűüýþÿĀāĂ" +
            "ăĄąĆćĊċČčĎďĐđĒēĔĕĖėĘęĚěĜĝĞğĠġĢģĤĥĦħĨĩĪīĬĭĮįİıĲĳĴĵĶķĸĹĺĻļĽľĿŀŁłŃńŅņŇňŉŊŋŌōŎŏŒœŔŕŖŗŚśŜŝŞşŠšŢţŤťŦŧŨũŪūŬŭŮůŲųŴŵŶŷŸŹźŻżŽ" +
            "žſƀƁƂƃƄƅƆƇƈƉƊƋƌƍƎƏƐƑƒƓƔƕƖƗƘƙƚƛƜƝƞƟƠơƢƣƤƥƦƧƨƩƪƫƬƭƮƯưƱƲƳƴƵƶƷƸƹƺƻƼƽƾƿǀǁǂǃǄǅǆǇǈǉǊǋǌǍǎǏǐǑǒǓǔǕǖǗǘǙǚǛǜǝǞǟǠǡǢǣǤǥǦǧǨǩǪǫǬǭǮǯǰǱ" +
            "ǲǳǴǵǶǷǸǹǺǻǼǽǾǿȀȁȂȃȄȅȆȇȈȉȊȋȌȍȎȏȐȑȒȓȔȕȖȗȘșȚțȜȝȞȟȠȡȢȣȤȥȦȧȨȩȪȫȬȭȮȯȰȱȲȳȴȵȶȷȸȹȺȻȼȽȾȿɀɁɂɃɄɅɆɇɈɉɊɋɌɍɎɏɐɑɒɓɔɕɖɗɘəɚɛɜɝɞɟɠɡɢɣɤɥ" +
            "ɦɧɨɩɪɫɬɭɮɯɰɱɲɳɴɵɶɷɸɹɺɻɼɽɾɿʀʁʂʃʄʅʆʇʈʉʊʋʌʍʎʏʐʑʒʓʔʕʖʗʘʙʚʛʜʝʞʟʠʡʢʣʤʥʦʧʨʩʪʫʬʭʮʯ";
    public static final String SPECIAL_CHARACTERS = SPECIAL_VALID_CHARACTERS +
            "'/+/!/%//=()ÖÜÓ~ˇ^˘°˛`˙´˝//¨¸÷×$ß¤;>*?:_/*-,//öüóéáűőúäÄèËëäëïö ü ø, Ø, å, Å," +
            "æ, ÆƩ'''-''`''~''¨''´''·' 'ʹ''ʺ''ʾ'" + APOSTROPHE +
            "ʿˈˌḂḃḆḇḊḋḌḍḎḏḐḑḗḜḝḞḟḠḡḢḣḤḥḦḧḨḩḪḫḯḰḱḲḳḴḵḶḷḺḻṀṁṂṃṄṅṆṇṈṉṒṓṔṕṖṗṘṙṚṛṞṟṠṡṢṣṪṫṬṭṮṯẀẁẂẃẄẅẆẇẌẍẎẏẐẑẒẓẔẕẖẗẠạẢảẤấẦầẨẩẪẫẬậẮắẰằẲẳẴẵẶặẸẹẺ" +
            "ẻẼẽẾếỀềỂểỄễỆệỈỉỊịỌọỎỏỐốỒồỔổỖỗỘộỚớỜờỞởỠỡỢợỤụỦủỨứỪừỬửỮữỰựỲỳỴỵỶỷỸỹ’‡" + APOSTROPHE +
            UNICODE_LETTER + "\u00C0\u00C1\u00C2\u00C7\u00C8\u00C9\u00CA\u00CB\u00CC\u00CDÎ\u00CF\u00D0\u00D1\u00D2\u00D3\u00D4\u00D5\u00D6\u00D7\u00D8\u00D9\u00DA\u00DB\u00DC\u00DD\u00DE\u00DF\u00E0\u00E1\u00E2\u00E3\u00E4\u00E5\u00E6\u00E7" +
            "\u00E8\u00E9\u00EA\u00EB\u00EC\u00ED\u00EE\u00EF\u00F0\u00F1\u00F2\u00F3\u00F4\u00F5\u00F6\u00F7\u00F8\u00F9\u00FA\u00FB\u00FC\u00FD\u00FE\u00FF\u0100\u0101\u0102\u0103\u0104\u0105\u0106\u0107\u0108\u0109\u010A\u010B\u010C\u010D\u010E\u010F" +
            "\u0110\u0111\u0112\u0113\u0114\u0115\u0116\u0117\u0118\u0119\u011A\u011B\u011C\u011D\u011E\u011F\u0120";


    private UserConstants() {
        throw new IllegalStateException("Utility class");
    }
}
