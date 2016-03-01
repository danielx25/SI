using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsFormsApplication1.Vista;
using Npgsql;

namespace WindowsFormsApplication1
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            System.Console.WriteLine("hola a todos");
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new AgregarTesis());
            BaseDatos basedatos = new BaseDatos();
            NpgsqlConnection conexion = basedatos.abrirConexion();

            string sql = "INSERT INTO tesis(titulo, estudiantes, profesores, fecha, etiquetas) VALUES('trafico vehicular', 'daniel', 'dytto', '21/02/1992', 'agentes');";
            string sql1 = "INSERT INTO pimpa VALUES('daniel', 19);";

            //NpgsqlDataAdapter da = new NpgsqlDataAdapter(sql, conexion); para consultas
            NpgsqlCommand da = new NpgsqlCommand(sql, conexion);
            da.ExecuteNonQuery();
            System.Console.ReadLine();

            //Application.Run(new Form1());
            //AgregarTesis l = new AgregarTesis();
        }


    }
}
