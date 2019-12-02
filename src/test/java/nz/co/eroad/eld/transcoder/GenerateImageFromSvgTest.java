package nz.co.eroad.eld.transcoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class GenerateImageFromSvgTest {

    @Test
    public void transcodeSVGDocument() throws IOException {
        GenerateImageFromSvg generateImageFromSvg = new GenerateImageFromSvg();
        assertNotNull(generateImageFromSvg.transcodeSVG(getClass().getResourceAsStream("/hos-graph.svg") ,900,100));
    }
}