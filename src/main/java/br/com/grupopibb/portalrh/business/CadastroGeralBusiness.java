/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.business;

import br.com.grupopibb.portalrh.model.Dependente;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class CadastroGeralBusiness {

    public int indexOfDependente(List<Dependente> dependentes, Dependente dependente) {
        if (dependentes == null || dependentes.isEmpty() || dependente == null
                || dependente.getCadastroGeral() == null || dependente.getCadastroGeral().getCpf() == null) {
            return -1;
        }
        for (Dependente dep : dependentes) {
            if (dep.getCadastroGeral() == null || dep.getCadastroGeral().getCpf() == null) {
                return -1;
            }
            if (dep.getSequencial() == dependente.getSequencial() && dep.getCadastroGeral().getCpf().equals(dependente.getCadastroGeral().getCpf())) {
                return dependentes.indexOf(dep);
            }
        }
        return -1;
    }
/*
    private Image toImage() {
        try {
            BufferedImage imagem = ImageIO.read(new File("C:/Arquivos de programas/NetBeans 7.3/img.png"));
            return imagem.getScaledInstance(10, 20, 1);
        } catch (IOException ex) {
            Logger.getLogger(CadastroGeralBusiness.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    private RenderedImage toRenderedImage(Image image) {
        BufferedImage tmp = new BufferedImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = tmp.getGraphics();
        g.drawImage(image, 10, 20, null);
        return tmp;
    }
    
    private BufferedImage toBufferImage(Image image) {
        BufferedImage tmp = new BufferedImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        return tmp;
    }

    public byte[] createImage() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(toRenderedImage(toImage()), "jpg", baos);
            return baos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(CadastroGeralBusiness.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Object readImage() throws IOException {
        FileInputStream fileInStream = null;
        try {
            fileInStream = new FileInputStream("img.png");
            //2 - Crie um ObjectInputStream
            ObjectInputStream is = new ObjectInputStream(fileInStream);
            //3 - Leia os objetos
            Object tres = is.readByte();

            try {
                tres = is.readObject();
                return tres;
                //4 - Converta os objetos
                //5 - Feche ObjectInputStream
            } catch (IOException ex) {
                Logger.getLogger(CadastroGeralBusiness.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CadastroGeralBusiness.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CadastroGeralBusiness.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public StreamedContent getContent() throws IOException {
       BufferedImage bufferedImg = toBufferImage(toImage());
       ByteArrayOutputStream os = new ByteArrayOutputStream();
       ImageIO.write(bufferedImg, "png", os);
       StreamedContent graphicImage = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()));
        return graphicImage;
    }
    * */
}
