package ifound.jsf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;
import ifound.entity.*;

@ViewScoped
@ManagedBean(name = "getuserdata")
public class GetUserData {

    public static int id;
    public static boolean work = false;
    public static String procura = "";
    ArrayList<ComponentesProjetos> registro;
    ArrayList<Componentes> registro2;
    ArrayList<Componentes> registro3;
    public static String localizacao = "";

// <============================================================================================================================================================================>
    public ArrayList<ComponentesProjetos> ordemProducao() {

        if (id == 0) {
            return registro;
        }

        if (registro != null) {
            return registro;
        }

        registro = new ArrayList<ComponentesProjetos>();
        Main.db = null;
        BD.ConectarBD();
        String sql = "SElECT quantidade,id_componentes FROM componentes_projetos WHERE id_projeto = " + id;

        try {
            Main.sql = Main.db.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        ResultSet rs = null;
        try {
            rs = Main.sql.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) {
                ComponentesProjetos process = new ComponentesProjetos();
                process.setObservacao(nomeComponente(rs.getString("id_componentes")));
                process.setQuantidade(rs.getInt("quantidade"));
                int quantidade = obterqntd(rs.getString("id_componentes"));
                quantidade = quantidade - process.getQuantidade();
                if (work == true) {
                    subtrairEstoque(rs.getString("id_componentes"), quantidade);
                }

                registro.add(process);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        try {
            Main.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetUserData.class.getName()).log(Level.SEVERE, null, ex);

        }
        work = false;
        return registro;

    }

    // <============================================================================================================================================================================>
    public ArrayList<Componentes> procurarComponente() {
        if (procura.equals("")) {
            return registro2;
        }

        if (registro2 != null) {
            return registro2;
        }
        registro2 = new ArrayList<Componentes>();
        Main.db = null;
        BD.ConectarBD();
        String sql = "SELECT * FROM componentes WHERE nome ILIKE '%" + procura + "%'";

        try {
            Main.sql = Main.db.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        ResultSet rs = null;
        try {
            rs = Main.sql.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) {
                Componentes process = new Componentes();
                process.setEndereco(rs.getString("endereco"));
                process.setQuantidade(rs.getInt("quantidade"));
                process.setValor(rs.getDouble("valor"));
                process.setNome(rs.getString("nome"));
                registro2.add(process);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        try {
            Main.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetUserData.class.getName()).log(Level.SEVERE, null, ex);

        }
        return registro2;

    }
    public ArrayList<Componentes> baixoEstoque() {
        if (registro3 != null) {
            return registro3;
        }
        registro3 = new ArrayList<Componentes>();
        Main.db = null;
        BD.ConectarBD();
        String sql = "SELECT * FROM componentes WHERE quantidade <= 20";

        try {
            Main.sql = Main.db.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        ResultSet rs = null;
        try {
            rs = Main.sql.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) {
                Componentes process = new Componentes();
                process.setEndereco(rs.getString("endereco"));
                process.setQuantidade(rs.getInt("quantidade"));
                process.setValor(rs.getDouble("valor"));
                process.setNome(rs.getString("nome"));
                registro3.add(process);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        try {
            Main.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetUserData.class.getName()).log(Level.SEVERE, null, ex);

        }
        return registro3;

    }

// <============================================================================================================================================================================>
    public String nomeComponente(String identificador) {
        String text = "";
        Main.db = null;
        BD.ConectarBD();
        String sql = "SELECT nome,valor,endereco FROM componentes WHERE id = " + identificador;

        try {
            Main.sql = Main.db.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        ResultSet rs = null;
        try {

            rs = Main.sql.executeQuery(sql);
            while (rs.next()) {
                text = rs.getString("nome") + " | " + rs.getString("valor") + " | " + rs.getString("endereco");

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        try {
            Main.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetUserData.class
                    .getName()).log(Level.SEVERE, null, ex);

        }

        return text;
    }

// <============================================================================================================================================================================>
    public void definirValor(int idProjeto) {
        registro = null;
        id = idProjeto;
        work = true;

    }
    //<============================================================================================================================================================================>

    public void definirProcura(String procurar) {
        registro2 = null;
        procura = procurar;

    }

    public void definirImagem(String imagem) {
        localizacao = imagem;

    }

    public String retornaImagem() throws InterruptedException {
        if (localizacao.equals("PRATELEIRA 1")) {
            return "/resources/images/p1.png";
        }
        if (localizacao.equals("PRATELEIRA 2")) {
            return "/resources/images/p2.png";
        }
        if (localizacao.equals("PRATELEIRA 3")) {
            return "/resources/images/p3.png";
        }
        if (localizacao.equals("PRATELEIRA 4")) {
            return "/resources/images/p4.png";
        }
        if (localizacao.equals("PRATELEIRA 5")) {
            return "/resources/images/p5.png";
        }
        if (localizacao.equals("PRATELEIRA 6")) {
            return "/resources/images/p6.png";
        }
        if (localizacao.equals("PRATELEIRA 7")) {
            return "/resources/images/p7.png";
        }
        if (localizacao.equals("PRATELEIRA 8")) {
            return "/resources/images/p8.png";
        }
        if (localizacao.equals("PRATELEIRA 9")) {
            return "/resources/images/p9.png";
        }
        if (localizacao.equals("PRATELEIRA 10")) {
            return "/resources/images/p10.png";
        }
        if (localizacao.equals("PRATELEIRA 11")) {
            return "/resources/images/p11.png";
        }
        if (localizacao.equals("PRATELEIRA 12")) {
            return "/resources/images/p12.png";
        }
        if (localizacao.equals("COLUNA 1")) {
            return "/resources/images/c1.png";
        }
        if (localizacao.equals("COLUNA 2")) {
            return "/resources/images/c2.png";
        }
        if (localizacao.equals("COLUNA 3")) {
            return "/resources/images/c3.png";
        }
        if (localizacao.equals("COLUNA 4")) {
            return "/resources/images/c4.png";
        }
        if (localizacao.equals("COLUNA 5")) {
            return "/resources/images/c5.png";
        }
        if (localizacao.equals("COLUNA 6")) {
            return "/resources/images/c6.png";
        }
        if (localizacao.equals("COLUNA 7")) {
            return "/resources/images/c7.png";
        }
        if (localizacao.equals("COLUNA 8")) {
            return "/resources/images/c8.png";
        }

        return "";

    }

// <============================================================================================================================================================================>
    public void subtrairEstoque(String identificador, int quantidade) {
        Main.db = null;
        BD.ConectarBD();
        String sql = "UPDATE componentes SET quantidade = " + quantidade + " WHERE id = " + identificador;

        try {
            Main.sql = Main.db.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        ResultSet rs = null;
        try {
            System.out.println(sql);
            Main.sql.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        try {
            Main.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetUserData.class
                    .getName()).log(Level.SEVERE, null, ex);

        }

    }

    public int obterqntd(String identificador) {
        Main.db = null;
        int text = 0;
        BD.ConectarBD();
        String sql = "SELECT quantidade FROM componentes WHERE id = " + identificador;

        try {
            Main.sql = Main.db.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        ResultSet rs = null;
        try {
            System.out.println(sql);
            rs = Main.sql.executeQuery(sql);
            while (rs.next()) {
                text = rs.getInt("quantidade");

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        try {
            Main.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetUserData.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        return text;
    }
}
