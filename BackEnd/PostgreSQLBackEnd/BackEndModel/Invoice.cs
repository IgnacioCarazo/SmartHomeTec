﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndModel
{
    public class Invoice
    {
        public int invoiceNumber { get; set; }
        public string deviceTypeName { get; set; }
        public int price { get; set; }
        public string date { get; set; }
    }
}
