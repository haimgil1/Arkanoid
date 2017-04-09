import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * A BlocksDefinitionReader class.
 *
 * @author Shurgil and barisya
 */
public class BlocksDefinitionReader {
    private List<Block> blockList;
    private BlocksFromSymbolsFactory all;
/**
 * @param reader the reader.
 * @throws IOException throw
 */
    public BlocksDefinitionReader(java.io.Reader reader) throws IOException {
        this.all = fromReader(reader);

        this.blockList = this.all.getblockList();
    }
/**
 * @param reader r
 * @return block from symbol factory
 * @throws IOException throw
 */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader)
            throws IOException {
        BlocksFromSymbolsFactory retval = new BlocksFromSymbolsFactory();
        List<Block> blockList1 = new ArrayList<Block>();

        BufferedReader br = (BufferedReader) reader;

        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            if (line.startsWith("default")) {
                retval.setdefault(line);
            }
            if (line.startsWith("bdef")) {
                retval.setblock(line);
            }
            if (line.startsWith("sdef")) {
                retval.setSpace(line);
            }
            sb.append(line + "\n");
            line = br.readLine();
        }
        br.close();
        retval.setblockList(blockList1);
        return retval;
    }
/**
 * @return list of block.
 */
    public List<Block> getBlocklist() {
        return this.blockList;
    }
/**
 * @param blockStartX x
 * @param blockStartY y
 * @param rowHeight rows
 * @param str string of the block places
 * @return list of block
 * @throws IOException throw
 */
    public List<Block> getblockplaces(int blockStartX, int blockStartY,
            int rowHeight, String str) throws IOException {
        List<Block> retblocks = new ArrayList<Block>();
        int x = blockStartX;
        for (int i = 0; i < str.length(); i++) {
            String strchar = str.substring(i, i + 1);
            char a = str.charAt(i);
            if (a == '\n') {
                x = blockStartX;
                blockStartY = blockStartY + rowHeight;
                continue;
            }
            if (this.all.isBlockSymbol(strchar)) {
                Block block1 = this.all.getBlock(strchar, x, blockStartY);
                retblocks.add(block1);
                x = (int) (x + block1.getCollisionRectangle().getWidth());
            }
            if (this.all.isSpaceSymbol(strchar)) {
                int add = this.all.getSpaceWidth(strchar);
                x = x + add;
            }
        }

        return retblocks;
    }
}
