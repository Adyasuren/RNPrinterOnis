package com.print.api;

public class ESCHelper {
    public static final byte CAN = 24;

    /* renamed from: CR */
    public static final byte f3CR = 13;
    public static final byte DLE = 16;
    public static final byte ENQ = 5;
    public static final byte EOT = 4;
    public static final byte ESC = 27;

    /* renamed from: FF */
    public static final byte f4FF = 12;

    /* renamed from: FS */
    public static final byte f5FS = 28;

    /* renamed from: GS */
    public static final byte f6GS = 29;

    /* renamed from: HT */
    public static final byte f7HT = 9;

    /* renamed from: LF */
    public static final byte f8LF = 10;

    /* renamed from: SP */
    public static final byte f9SP = 32;

    public static byte[] FeedPaperCutAll() {
        return new byte[]{f6GS, 86, 65, 0};
    }

    public static byte[] FeedPaperCutPartial() {
        return new byte[]{f6GS, 86, 66, 0};
    }
}
