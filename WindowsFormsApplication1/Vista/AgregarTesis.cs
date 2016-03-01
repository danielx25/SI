using Npgsql;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApplication1.Vista
{
    public partial class AgregarTesis : Form
    {
        private NpgsqlConnection conexion;
        private string fila;

        public AgregarTesis()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            agregar_tesis();
        }

        

        public void setConection(NpgsqlConnection conexion)
        {
            this.conexion = conexion;
        }

        public void agregar_tesis()
        {
            if (textBox1.TextLength>1)
            fila = "'" + textBox1.Text + "', ";
            else
            {
                MessageBox.Show("Campo incompleto", "Error", MessageBoxButtons.OKCancel, MessageBoxIcon.Asterisk);
                textBox1.SelectionStart = 0;
            }
                
            fila += "'" + textBox2.Text + "', ";
            fila+= "'" + textBox3.Text + "', ";
            fila+= "'" + monthCalendar1.SelectionRange.Start.ToShortDateString() + "', ";
            fila+= "'" + textBox4.Text + "'";
            System.Console.WriteLine(fila);

        }

    }
}
