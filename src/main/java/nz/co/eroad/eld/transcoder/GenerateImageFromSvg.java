package nz.co.eroad.eld.transcoder;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;


public class GenerateImageFromSvg {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateImageFromSvg.class);

    public Optional<BufferedImage> transcodeSVG(InputStream stream, int width, int height) throws IOException {
        Transcoder t = new PNGTranscoder();
        t.addTranscodingHint( PNGTranscoder.KEY_WIDTH,  new Float(width) );
        t.addTranscodingHint( PNGTranscoder.KEY_HEIGHT, new Float(height) );

        TranscoderInput input = new TranscoderInput( stream);
        ByteArrayOutputStream ostream = null;
        try {
            ostream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput( ostream );
            t.transcode( input, output );
            byte[] imgData = ostream.toByteArray();
            return Optional.of(ImageIO.read(new ByteArrayInputStream(imgData)));
        } catch( Exception ex ){
            LOGGER.warn(String.format("Could not transcode svg to image, exception is %s", ex.getMessage()));
            return Optional.empty();
        }finally {
            ostream.flush();
            ostream.close();
        }
    }
}
