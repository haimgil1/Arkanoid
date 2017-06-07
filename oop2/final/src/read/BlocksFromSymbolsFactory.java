package read;

import accessories.Block;
import geometry.Rectangle;
import sprites.Background;

import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;

/**
 * A BlocksFromSymbolsFactory class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class BlocksFromSymbolsFactory extends CheckRead {
    //Members.
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * constructor.
     */
    public BlocksFromSymbolsFactory() {
        this.blockCreators = new TreeMap<String, BlockCreator>();
        this.spacerWidths = new TreeMap<String, Integer>();
    }

    // returns

    /**
     * @param s Is a String indicate symbol.
     * @return True if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * @param s Is a String represent block symbol.
     * @return True if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }


    /**
     * returning block located at position (xpos, ypos) according to 's'.
     *
     * @param s    Is String represent block symbole.
     * @param xpos Is the x position to set to the block.
     * @param ypos Is the y position to set to the block.
     * @return Block according to the definitions associated with symbol s.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);

    }


    /**
     * @param s Is a string represent space symbol.
     * @return The width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * Creating Map between Integers and Backgrounds according to bdef Map.
     *
     * @param bdef is definitions Map of block.
     * @return Map between Integer represent hit point to his Background That suits him.
     */
    public Map<Integer, Background> defineBackground(Map<String, String> bdef) {
        Map<Integer, Background> backgroundMap = new TreeMap<Integer, Background>();

        //Goes over keys and find background definitions.
        for (Map.Entry<String, String> entry : bdef.entrySet()) {
            //Indicate the hit number default -1.
            if (entry.getKey().contains("fill")) {
                Integer hit = new Integer(-1);
                Background backgroundHit;
                if (entry.getKey().contains("fill:")) {
                    //Get background according to value.
                    backgroundHit = getBackground(entry.getValue());

                } else {
                    //Define -1 for default fill.
                    int hitNumber = Integer.parseInt(entry.getKey().substring(5, 6));
                    hit = new Integer(hitNumber);
                    backgroundHit = getBackground(entry.getValue());
                }
                backgroundMap.put(hit, backgroundHit);
            }
        }
        return backgroundMap;
    }

    /**
     * Create Block according to map of definitions and adding to blockcreator Map.
     *
     * @param bdef is Map with block definitions.
     */
    public void addBlock(Map<String, String> bdef) {
        String[] definitions = {"height", "width", "hit_points", "symbol"};
        //Getting height.
        final int height = Integer.parseInt(bdef.get("height"));
        //Getting width.
        final int width = Integer.parseInt(bdef.get("width"));
        //Getting hitPoint.
        final int hitPoint = Integer.parseInt(bdef.get("hit_points"));
        //Getting background.
        final Map<Integer, Background> background = defineBackground(bdef);
        try {
            Integer[] check = {height, width, hitPoint};
            checkPositiveInteger(check, definitions);
            checkDefinitionsExist(bdef, definitions);
        } catch (Exception exp) {
            exp.printStackTrace(System.err);
            System.exit(1);
        }

        Color strokeColor = null;

        //Bheck if there is definition of stroke and adding if exist.
        if (bdef.keySet().contains("stroke")) {
            try {
                strokeColor = new ColorsParser().colorFromString(bdef.get("stroke"));
            } catch (Exception exp) {
                exp.printStackTrace(System.err);
                System.exit(1);
            }

        }
        final Color stroke = strokeColor;
        //Inner class.
        BlockCreator block = new BlockCreator() {

            /**
             * Create block according to x and y position
             *
             * @param xpos Is the x poition to be set.
             * @param ypos Is the u poition to be set.
             * @return Block with x and y coordinate.
             */
            public Block create(int xpos, int ypos) {
                Rectangle rectangle = new Rectangle(xpos, ypos, width, height);
                return new Block(rectangle, hitPoint, background, stroke);
            }

        };
        blockCreators.put(bdef.get("symbol"), block);
    }

    /**
     * Getting map of spacers and adding them to spcers wisth map member.
     *
     * @param spacers Is map of String represent spacer to his  width.
     */
    public void addSpacers(Map<String, Integer> spacers) {
        spacerWidths.putAll(spacers);
    }

}
