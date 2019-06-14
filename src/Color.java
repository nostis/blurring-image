public class Color {
    static int[] getARGBFromInt(int color){
        int[] argb = new int[4];

        argb[0] = ((color >> 24) & 0xff);
        argb[1] = ((color >> 16) & 0xff);
        argb[2] = ((color >> 8) & 0xff);
        argb[3] = ((color) & 0xff);

        return argb;
    }

    static int getIntFromARGB(int[] argb){
        return (argb[0] & 0xff) << 24 | (argb[1] & 0xff) << 16 | (argb[2] & 0xff) << 8 | (argb[3] & 0xff);
    }
}
