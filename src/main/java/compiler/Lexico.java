package compiler;

public class Lexico implements Constants
{
    private int position;
    private String input;

    public Lexico()
    {
        this(new java.io.StringReader(""));
    }

    public Lexico(String input)
    {
        setInput(input);
    }

    public void setInput(String input)
    {
        this.input = input;
        setPosition(0);
    }
    
    public Lexico(java.io.Reader input)
    {
        setInput(input);
    }

    public void setInput(java.io.Reader input)
    {
        StringBuffer bfr = new StringBuffer();
        try
        {
            int c = input.read();
            while (c != -1)
            {
                bfr.append((char)c);
                c = input.read();
            }
            this.input = bfr.toString();
        }
        catch (java.io.IOException e)
        {
            e.printStackTrace();
        }

        setPosition(0);
    }

    public void setPosition(int pos)
    {
        position = pos;
    }

    public Token nextToken() throws LexicalError
    {
        if ( ! hasInput() )
            return null;

        int start = position;

        int state = 0;
        int lastState = 0;
        int endState = -1;
        int end = -1;

        while (hasInput())
        {
            lastState = state;
            state = nextState(nextChar(), state);

            if (state < 0)
                break;

            else
            {
                if (tokenForState(state) >= 0)
                {
                    endState = state;
                    end = position;
                }
            }
        }
        if (endState < 0 || (endState != state && tokenForState(lastState) == -2))
            throw new LexicalError(SCANNER_ERROR[lastState], start);

        position = end;

        int token = tokenForState(endState);

        if (token == 0)
            return nextToken();
        else
        {
            String lexeme = input.substring(start, end);
            token = lookupToken(token, lexeme);
            return new Token(token, lexeme, start);
        }
    }

    private int nextState(char c, int state)
    {
        switch (state)
        {
            case 0:
                switch (c)
                {
                    case 9: return 1;
                    case 10: return 1;
                    case 13: return 1;
                    case 32: return 1;
                    case 33: return 2;
                    case 34: return 3;
                    case 35: return 4;
                    case 38: return 5;
                    case 39: return 6;
                    case 40: return 7;
                    case 41: return 8;
                    case 42: return 9;
                    case 43: return 10;
                    case 44: return 11;
                    case 45: return 12;
                    case 46: return 13;
                    case 47: return 14;
                    case 48: return 15;
                    case 49: return 15;
                    case 50: return 15;
                    case 51: return 15;
                    case 52: return 15;
                    case 53: return 15;
                    case 54: return 15;
                    case 55: return 15;
                    case 56: return 15;
                    case 57: return 15;
                    case 58: return 16;
                    case 59: return 17;
                    case 60: return 18;
                    case 61: return 19;
                    case 62: return 20;
                    case 64: return 21;
                    case 65: return 22;
                    case 66: return 22;
                    case 67: return 22;
                    case 68: return 22;
                    case 69: return 22;
                    case 70: return 22;
                    case 71: return 22;
                    case 72: return 22;
                    case 73: return 22;
                    case 74: return 22;
                    case 75: return 22;
                    case 76: return 22;
                    case 77: return 22;
                    case 78: return 22;
                    case 79: return 22;
                    case 80: return 22;
                    case 81: return 22;
                    case 82: return 22;
                    case 83: return 22;
                    case 84: return 22;
                    case 85: return 22;
                    case 86: return 22;
                    case 87: return 22;
                    case 88: return 22;
                    case 89: return 22;
                    case 90: return 22;
                    case 91: return 23;
                    case 93: return 24;
                    case 94: return 25;
                    case 95: return 26;
                    case 97: return 27;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 26;
                    case 101: return 28;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 29;
                    case 111: return 30;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 26;
                    case 115: return 26;
                    case 116: return 26;
                    case 117: return 26;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 31;
                    case 121: return 26;
                    case 122: return 26;
                    case 123: return 32;
                    case 124: return 33;
                    case 125: return 34;
                    case 126: return 35;
                    default: return -1;
                }
            case 1:
                switch (c)
                {
                    case 9: return 1;
                    case 10: return 1;
                    case 13: return 1;
                    case 32: return 1;
                    default: return -1;
                }
            case 2:
                switch (c)
                {
                    case 61: return 36;
                    default: return -1;
                }
            case 3:
                switch (c)
                {
                    case 9: return 3;
                    case 10: return 3;
                    case 13: return 3;
                    case 32: return 3;
                    case 33: return 3;
                    case 34: return 37;
                    case 35: return 3;
                    case 36: return 3;
                    case 37: return 3;
                    case 38: return 3;
                    case 39: return 3;
                    case 40: return 3;
                    case 41: return 3;
                    case 42: return 3;
                    case 43: return 3;
                    case 44: return 3;
                    case 45: return 3;
                    case 46: return 3;
                    case 47: return 3;
                    case 48: return 3;
                    case 49: return 3;
                    case 50: return 3;
                    case 51: return 3;
                    case 52: return 3;
                    case 53: return 3;
                    case 54: return 3;
                    case 55: return 3;
                    case 56: return 3;
                    case 57: return 3;
                    case 58: return 3;
                    case 59: return 3;
                    case 60: return 3;
                    case 61: return 3;
                    case 62: return 3;
                    case 63: return 3;
                    case 64: return 3;
                    case 65: return 3;
                    case 66: return 3;
                    case 67: return 3;
                    case 68: return 3;
                    case 69: return 3;
                    case 70: return 3;
                    case 71: return 3;
                    case 72: return 3;
                    case 73: return 3;
                    case 74: return 3;
                    case 75: return 3;
                    case 76: return 3;
                    case 77: return 3;
                    case 78: return 3;
                    case 79: return 3;
                    case 80: return 3;
                    case 81: return 3;
                    case 82: return 3;
                    case 83: return 3;
                    case 84: return 3;
                    case 85: return 3;
                    case 86: return 3;
                    case 87: return 3;
                    case 88: return 3;
                    case 89: return 3;
                    case 90: return 3;
                    case 91: return 3;
                    case 92: return 3;
                    case 93: return 3;
                    case 94: return 3;
                    case 95: return 3;
                    case 96: return 3;
                    case 97: return 3;
                    case 98: return 3;
                    case 99: return 3;
                    case 100: return 3;
                    case 101: return 3;
                    case 102: return 3;
                    case 103: return 3;
                    case 104: return 3;
                    case 105: return 3;
                    case 106: return 3;
                    case 107: return 3;
                    case 108: return 3;
                    case 109: return 3;
                    case 110: return 3;
                    case 111: return 3;
                    case 112: return 3;
                    case 113: return 3;
                    case 114: return 3;
                    case 115: return 3;
                    case 116: return 3;
                    case 117: return 3;
                    case 118: return 3;
                    case 119: return 3;
                    case 120: return 3;
                    case 121: return 3;
                    case 122: return 3;
                    case 123: return 3;
                    case 124: return 3;
                    case 125: return 3;
                    case 126: return 3;
                    case 161: return 3;
                    case 162: return 3;
                    case 163: return 3;
                    case 164: return 3;
                    case 165: return 3;
                    case 166: return 3;
                    case 167: return 3;
                    case 168: return 3;
                    case 169: return 3;
                    case 170: return 3;
                    case 171: return 3;
                    case 172: return 3;
                    case 173: return 3;
                    case 174: return 3;
                    case 175: return 3;
                    case 176: return 3;
                    case 177: return 3;
                    case 178: return 3;
                    case 179: return 3;
                    case 180: return 3;
                    case 181: return 3;
                    case 182: return 3;
                    case 183: return 3;
                    case 184: return 3;
                    case 185: return 3;
                    case 186: return 3;
                    case 187: return 3;
                    case 188: return 3;
                    case 189: return 3;
                    case 190: return 3;
                    case 191: return 3;
                    case 192: return 3;
                    case 193: return 3;
                    case 194: return 3;
                    case 195: return 3;
                    case 196: return 3;
                    case 197: return 3;
                    case 198: return 3;
                    case 199: return 3;
                    case 200: return 3;
                    case 201: return 3;
                    case 202: return 3;
                    case 203: return 3;
                    case 204: return 3;
                    case 205: return 3;
                    case 206: return 3;
                    case 207: return 3;
                    case 208: return 3;
                    case 209: return 3;
                    case 210: return 3;
                    case 211: return 3;
                    case 212: return 3;
                    case 213: return 3;
                    case 214: return 3;
                    case 215: return 3;
                    case 216: return 3;
                    case 217: return 3;
                    case 218: return 3;
                    case 219: return 3;
                    case 220: return 3;
                    case 221: return 3;
                    case 222: return 3;
                    case 223: return 3;
                    case 224: return 3;
                    case 225: return 3;
                    case 226: return 3;
                    case 227: return 3;
                    case 228: return 3;
                    case 229: return 3;
                    case 230: return 3;
                    case 231: return 3;
                    case 232: return 3;
                    case 233: return 3;
                    case 234: return 3;
                    case 235: return 3;
                    case 236: return 3;
                    case 237: return 3;
                    case 238: return 3;
                    case 239: return 3;
                    case 240: return 3;
                    case 241: return 3;
                    case 242: return 3;
                    case 243: return 3;
                    case 244: return 3;
                    case 245: return 3;
                    case 246: return 3;
                    case 247: return 3;
                    case 248: return 3;
                    case 249: return 3;
                    case 250: return 3;
                    case 251: return 3;
                    case 252: return 3;
                    case 253: return 3;
                    case 254: return 3;
                    case 255: return 3;
                    default: return -1;
                }
            case 4:
                switch (c)
                {
                    case 9: return 4;
                    case 32: return 4;
                    case 33: return 4;
                    case 34: return 4;
                    case 35: return 4;
                    case 36: return 4;
                    case 37: return 4;
                    case 38: return 4;
                    case 39: return 4;
                    case 40: return 4;
                    case 41: return 4;
                    case 42: return 4;
                    case 43: return 4;
                    case 44: return 4;
                    case 45: return 4;
                    case 46: return 4;
                    case 47: return 4;
                    case 48: return 4;
                    case 49: return 4;
                    case 50: return 4;
                    case 51: return 4;
                    case 52: return 4;
                    case 53: return 4;
                    case 54: return 4;
                    case 55: return 4;
                    case 56: return 4;
                    case 57: return 4;
                    case 58: return 4;
                    case 59: return 4;
                    case 60: return 4;
                    case 61: return 4;
                    case 62: return 4;
                    case 63: return 4;
                    case 64: return 4;
                    case 65: return 4;
                    case 66: return 4;
                    case 67: return 4;
                    case 68: return 4;
                    case 69: return 4;
                    case 70: return 4;
                    case 71: return 4;
                    case 72: return 4;
                    case 73: return 4;
                    case 74: return 4;
                    case 75: return 4;
                    case 76: return 4;
                    case 77: return 4;
                    case 78: return 4;
                    case 79: return 4;
                    case 80: return 4;
                    case 81: return 4;
                    case 82: return 4;
                    case 83: return 4;
                    case 84: return 4;
                    case 85: return 4;
                    case 86: return 4;
                    case 87: return 4;
                    case 88: return 4;
                    case 89: return 4;
                    case 90: return 4;
                    case 91: return 4;
                    case 92: return 4;
                    case 93: return 4;
                    case 94: return 4;
                    case 95: return 4;
                    case 96: return 4;
                    case 97: return 4;
                    case 98: return 4;
                    case 99: return 4;
                    case 100: return 4;
                    case 101: return 4;
                    case 102: return 4;
                    case 103: return 4;
                    case 104: return 4;
                    case 105: return 4;
                    case 106: return 4;
                    case 107: return 4;
                    case 108: return 4;
                    case 109: return 4;
                    case 110: return 4;
                    case 111: return 4;
                    case 112: return 4;
                    case 113: return 4;
                    case 114: return 4;
                    case 115: return 4;
                    case 116: return 4;
                    case 117: return 4;
                    case 118: return 4;
                    case 119: return 4;
                    case 120: return 4;
                    case 121: return 4;
                    case 122: return 4;
                    case 123: return 4;
                    case 124: return 4;
                    case 125: return 4;
                    case 126: return 4;
                    case 161: return 4;
                    case 162: return 4;
                    case 163: return 4;
                    case 164: return 4;
                    case 165: return 4;
                    case 166: return 4;
                    case 167: return 4;
                    case 168: return 4;
                    case 169: return 4;
                    case 170: return 4;
                    case 171: return 4;
                    case 172: return 4;
                    case 173: return 4;
                    case 174: return 4;
                    case 175: return 4;
                    case 176: return 4;
                    case 177: return 4;
                    case 178: return 4;
                    case 179: return 4;
                    case 180: return 4;
                    case 181: return 4;
                    case 182: return 4;
                    case 183: return 4;
                    case 184: return 4;
                    case 185: return 4;
                    case 186: return 4;
                    case 187: return 4;
                    case 188: return 4;
                    case 189: return 4;
                    case 190: return 4;
                    case 191: return 4;
                    case 192: return 4;
                    case 193: return 4;
                    case 194: return 4;
                    case 195: return 4;
                    case 196: return 4;
                    case 197: return 4;
                    case 198: return 4;
                    case 199: return 4;
                    case 200: return 4;
                    case 201: return 4;
                    case 202: return 4;
                    case 203: return 4;
                    case 204: return 4;
                    case 205: return 4;
                    case 206: return 4;
                    case 207: return 4;
                    case 208: return 4;
                    case 209: return 4;
                    case 210: return 4;
                    case 211: return 4;
                    case 212: return 4;
                    case 213: return 4;
                    case 214: return 4;
                    case 215: return 4;
                    case 216: return 4;
                    case 217: return 4;
                    case 218: return 4;
                    case 219: return 4;
                    case 220: return 4;
                    case 221: return 4;
                    case 222: return 4;
                    case 223: return 4;
                    case 224: return 4;
                    case 225: return 4;
                    case 226: return 4;
                    case 227: return 4;
                    case 228: return 4;
                    case 229: return 4;
                    case 230: return 4;
                    case 231: return 4;
                    case 232: return 4;
                    case 233: return 4;
                    case 234: return 4;
                    case 235: return 4;
                    case 236: return 4;
                    case 237: return 4;
                    case 238: return 4;
                    case 239: return 4;
                    case 240: return 4;
                    case 241: return 4;
                    case 242: return 4;
                    case 243: return 4;
                    case 244: return 4;
                    case 245: return 4;
                    case 246: return 4;
                    case 247: return 4;
                    case 248: return 4;
                    case 249: return 4;
                    case 250: return 4;
                    case 251: return 4;
                    case 252: return 4;
                    case 253: return 4;
                    case 254: return 4;
                    case 255: return 4;
                    default: return -1;
                }
            case 5:
                switch (c)
                {
                    case 38: return 38;
                    default: return -1;
                }
            case 6:
                switch (c)
                {
                    case 9: return 6;
                    case 10: return 6;
                    case 13: return 6;
                    case 32: return 6;
                    case 33: return 6;
                    case 34: return 6;
                    case 35: return 6;
                    case 36: return 6;
                    case 37: return 6;
                    case 38: return 6;
                    case 39: return 37;
                    case 40: return 6;
                    case 41: return 6;
                    case 42: return 6;
                    case 43: return 6;
                    case 44: return 6;
                    case 45: return 6;
                    case 46: return 6;
                    case 47: return 6;
                    case 48: return 6;
                    case 49: return 6;
                    case 50: return 6;
                    case 51: return 6;
                    case 52: return 6;
                    case 53: return 6;
                    case 54: return 6;
                    case 55: return 6;
                    case 56: return 6;
                    case 57: return 6;
                    case 58: return 6;
                    case 59: return 6;
                    case 60: return 6;
                    case 61: return 6;
                    case 62: return 6;
                    case 63: return 6;
                    case 64: return 6;
                    case 65: return 6;
                    case 66: return 6;
                    case 67: return 6;
                    case 68: return 6;
                    case 69: return 6;
                    case 70: return 6;
                    case 71: return 6;
                    case 72: return 6;
                    case 73: return 6;
                    case 74: return 6;
                    case 75: return 6;
                    case 76: return 6;
                    case 77: return 6;
                    case 78: return 6;
                    case 79: return 6;
                    case 80: return 6;
                    case 81: return 6;
                    case 82: return 6;
                    case 83: return 6;
                    case 84: return 6;
                    case 85: return 6;
                    case 86: return 6;
                    case 87: return 6;
                    case 88: return 6;
                    case 89: return 6;
                    case 90: return 6;
                    case 91: return 6;
                    case 92: return 6;
                    case 93: return 6;
                    case 94: return 6;
                    case 95: return 6;
                    case 96: return 6;
                    case 97: return 6;
                    case 98: return 6;
                    case 99: return 6;
                    case 100: return 6;
                    case 101: return 6;
                    case 102: return 6;
                    case 103: return 6;
                    case 104: return 6;
                    case 105: return 6;
                    case 106: return 6;
                    case 107: return 6;
                    case 108: return 6;
                    case 109: return 6;
                    case 110: return 6;
                    case 111: return 6;
                    case 112: return 6;
                    case 113: return 6;
                    case 114: return 6;
                    case 115: return 6;
                    case 116: return 6;
                    case 117: return 6;
                    case 118: return 6;
                    case 119: return 6;
                    case 120: return 6;
                    case 121: return 6;
                    case 122: return 6;
                    case 123: return 6;
                    case 124: return 6;
                    case 125: return 6;
                    case 126: return 6;
                    case 161: return 6;
                    case 162: return 6;
                    case 163: return 6;
                    case 164: return 6;
                    case 165: return 6;
                    case 166: return 6;
                    case 167: return 6;
                    case 168: return 6;
                    case 169: return 6;
                    case 170: return 6;
                    case 171: return 6;
                    case 172: return 6;
                    case 173: return 6;
                    case 174: return 6;
                    case 175: return 6;
                    case 176: return 6;
                    case 177: return 6;
                    case 178: return 6;
                    case 179: return 6;
                    case 180: return 6;
                    case 181: return 6;
                    case 182: return 6;
                    case 183: return 6;
                    case 184: return 6;
                    case 185: return 6;
                    case 186: return 6;
                    case 187: return 6;
                    case 188: return 6;
                    case 189: return 6;
                    case 190: return 6;
                    case 191: return 6;
                    case 192: return 6;
                    case 193: return 6;
                    case 194: return 6;
                    case 195: return 6;
                    case 196: return 6;
                    case 197: return 6;
                    case 198: return 6;
                    case 199: return 6;
                    case 200: return 6;
                    case 201: return 6;
                    case 202: return 6;
                    case 203: return 6;
                    case 204: return 6;
                    case 205: return 6;
                    case 206: return 6;
                    case 207: return 6;
                    case 208: return 6;
                    case 209: return 6;
                    case 210: return 6;
                    case 211: return 6;
                    case 212: return 6;
                    case 213: return 6;
                    case 214: return 6;
                    case 215: return 6;
                    case 216: return 6;
                    case 217: return 6;
                    case 218: return 6;
                    case 219: return 6;
                    case 220: return 6;
                    case 221: return 6;
                    case 222: return 6;
                    case 223: return 6;
                    case 224: return 6;
                    case 225: return 6;
                    case 226: return 6;
                    case 227: return 6;
                    case 228: return 6;
                    case 229: return 6;
                    case 230: return 6;
                    case 231: return 6;
                    case 232: return 6;
                    case 233: return 6;
                    case 234: return 6;
                    case 235: return 6;
                    case 236: return 6;
                    case 237: return 6;
                    case 238: return 6;
                    case 239: return 6;
                    case 240: return 6;
                    case 241: return 6;
                    case 242: return 6;
                    case 243: return 6;
                    case 244: return 6;
                    case 245: return 6;
                    case 246: return 6;
                    case 247: return 6;
                    case 248: return 6;
                    case 249: return 6;
                    case 250: return 6;
                    case 251: return 6;
                    case 252: return 6;
                    case 253: return 6;
                    case 254: return 6;
                    case 255: return 6;
                    default: return -1;
                }
            case 9:
                switch (c)
                {
                    case 42: return 39;
                    default: return -1;
                }
            case 14:
                switch (c)
                {
                    case 42: return 40;
                    case 47: return 41;
                    default: return -1;
                }
            case 15:
                switch (c)
                {
                    case 46: return 42;
                    case 48: return 15;
                    case 49: return 15;
                    case 50: return 15;
                    case 51: return 15;
                    case 52: return 15;
                    case 53: return 15;
                    case 54: return 15;
                    case 55: return 15;
                    case 56: return 15;
                    case 57: return 15;
                    default: return -1;
                }
            case 18:
                switch (c)
                {
                    case 61: return 43;
                    case 62: return 44;
                    default: return -1;
                }
            case 19:
                switch (c)
                {
                    case 61: return 45;
                    default: return -1;
                }
            case 20:
                switch (c)
                {
                    case 61: return 46;
                    default: return -1;
                }
            case 21:
                switch (c)
                {
                    case 118: return 47;
                    default: return -1;
                }
            case 22:
                switch (c)
                {
                    case 65: return 22;
                    case 66: return 22;
                    case 67: return 22;
                    case 68: return 22;
                    case 69: return 22;
                    case 70: return 22;
                    case 71: return 22;
                    case 72: return 22;
                    case 73: return 22;
                    case 74: return 22;
                    case 75: return 22;
                    case 76: return 22;
                    case 77: return 22;
                    case 78: return 22;
                    case 79: return 22;
                    case 80: return 22;
                    case 81: return 22;
                    case 82: return 22;
                    case 83: return 22;
                    case 84: return 22;
                    case 85: return 22;
                    case 86: return 22;
                    case 87: return 22;
                    case 88: return 22;
                    case 89: return 22;
                    case 90: return 22;
                    default: return -1;
                }
            case 26:
                switch (c)
                {
                    case 48: return 26;
                    case 49: return 26;
                    case 50: return 26;
                    case 51: return 26;
                    case 52: return 26;
                    case 53: return 26;
                    case 54: return 26;
                    case 55: return 26;
                    case 56: return 26;
                    case 57: return 26;
                    case 65: return 26;
                    case 66: return 26;
                    case 67: return 26;
                    case 68: return 26;
                    case 69: return 26;
                    case 70: return 26;
                    case 71: return 26;
                    case 72: return 26;
                    case 73: return 26;
                    case 74: return 26;
                    case 75: return 26;
                    case 76: return 26;
                    case 77: return 26;
                    case 78: return 26;
                    case 79: return 26;
                    case 80: return 26;
                    case 81: return 26;
                    case 82: return 26;
                    case 83: return 26;
                    case 84: return 26;
                    case 85: return 26;
                    case 86: return 26;
                    case 87: return 26;
                    case 88: return 26;
                    case 89: return 26;
                    case 90: return 26;
                    case 95: return 26;
                    case 97: return 26;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 26;
                    case 101: return 26;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 26;
                    case 111: return 26;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 26;
                    case 115: return 26;
                    case 116: return 26;
                    case 117: return 26;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 26;
                    case 121: return 26;
                    case 122: return 26;
                    default: return -1;
                }
            case 27:
                switch (c)
                {
                    case 48: return 26;
                    case 49: return 26;
                    case 50: return 26;
                    case 51: return 26;
                    case 52: return 26;
                    case 53: return 26;
                    case 54: return 26;
                    case 55: return 26;
                    case 56: return 26;
                    case 57: return 26;
                    case 65: return 26;
                    case 66: return 26;
                    case 67: return 26;
                    case 68: return 26;
                    case 69: return 26;
                    case 70: return 26;
                    case 71: return 26;
                    case 72: return 26;
                    case 73: return 26;
                    case 74: return 26;
                    case 75: return 26;
                    case 76: return 26;
                    case 77: return 26;
                    case 78: return 26;
                    case 79: return 26;
                    case 80: return 26;
                    case 81: return 26;
                    case 82: return 26;
                    case 83: return 26;
                    case 84: return 26;
                    case 85: return 26;
                    case 86: return 26;
                    case 87: return 26;
                    case 88: return 26;
                    case 89: return 26;
                    case 90: return 26;
                    case 95: return 26;
                    case 97: return 26;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 26;
                    case 101: return 26;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 48;
                    case 111: return 26;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 26;
                    case 115: return 26;
                    case 116: return 26;
                    case 117: return 26;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 26;
                    case 121: return 26;
                    case 122: return 26;
                    default: return -1;
                }
            case 28:
                switch (c)
                {
                    case 48: return 26;
                    case 49: return 26;
                    case 50: return 26;
                    case 51: return 26;
                    case 52: return 26;
                    case 53: return 26;
                    case 54: return 26;
                    case 55: return 26;
                    case 56: return 26;
                    case 57: return 26;
                    case 65: return 26;
                    case 66: return 26;
                    case 67: return 26;
                    case 68: return 26;
                    case 69: return 26;
                    case 70: return 26;
                    case 71: return 26;
                    case 72: return 26;
                    case 73: return 26;
                    case 74: return 26;
                    case 75: return 26;
                    case 76: return 26;
                    case 77: return 26;
                    case 78: return 26;
                    case 79: return 26;
                    case 80: return 26;
                    case 81: return 26;
                    case 82: return 26;
                    case 83: return 26;
                    case 84: return 26;
                    case 85: return 26;
                    case 86: return 26;
                    case 87: return 26;
                    case 88: return 26;
                    case 89: return 26;
                    case 90: return 26;
                    case 95: return 26;
                    case 97: return 26;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 26;
                    case 101: return 26;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 26;
                    case 111: return 26;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 26;
                    case 115: return 26;
                    case 116: return 26;
                    case 117: return 26;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 26;
                    case 121: return 26;
                    case 122: return 26;
                    default: return -1;
                }
            case 29:
                switch (c)
                {
                    case 48: return 26;
                    case 49: return 26;
                    case 50: return 26;
                    case 51: return 26;
                    case 52: return 26;
                    case 53: return 26;
                    case 54: return 26;
                    case 55: return 26;
                    case 56: return 26;
                    case 57: return 26;
                    case 65: return 26;
                    case 66: return 26;
                    case 67: return 26;
                    case 68: return 26;
                    case 69: return 26;
                    case 70: return 26;
                    case 71: return 26;
                    case 72: return 26;
                    case 73: return 26;
                    case 74: return 26;
                    case 75: return 26;
                    case 76: return 26;
                    case 77: return 26;
                    case 78: return 26;
                    case 79: return 26;
                    case 80: return 26;
                    case 81: return 26;
                    case 82: return 26;
                    case 83: return 26;
                    case 84: return 26;
                    case 85: return 26;
                    case 86: return 26;
                    case 87: return 26;
                    case 88: return 26;
                    case 89: return 26;
                    case 90: return 26;
                    case 95: return 26;
                    case 97: return 49;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 26;
                    case 101: return 26;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 26;
                    case 111: return 50;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 26;
                    case 115: return 26;
                    case 116: return 26;
                    case 117: return 26;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 26;
                    case 121: return 26;
                    case 122: return 26;
                    default: return -1;
                }
            case 30:
                switch (c)
                {
                    case 48: return 26;
                    case 49: return 26;
                    case 50: return 26;
                    case 51: return 26;
                    case 52: return 26;
                    case 53: return 26;
                    case 54: return 26;
                    case 55: return 26;
                    case 56: return 26;
                    case 57: return 26;
                    case 65: return 26;
                    case 66: return 26;
                    case 67: return 26;
                    case 68: return 26;
                    case 69: return 26;
                    case 70: return 26;
                    case 71: return 26;
                    case 72: return 26;
                    case 73: return 26;
                    case 74: return 26;
                    case 75: return 26;
                    case 76: return 26;
                    case 77: return 26;
                    case 78: return 26;
                    case 79: return 26;
                    case 80: return 26;
                    case 81: return 26;
                    case 82: return 26;
                    case 83: return 26;
                    case 84: return 26;
                    case 85: return 26;
                    case 86: return 26;
                    case 87: return 26;
                    case 88: return 26;
                    case 89: return 26;
                    case 90: return 26;
                    case 95: return 26;
                    case 97: return 26;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 26;
                    case 101: return 26;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 26;
                    case 111: return 26;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 51;
                    case 115: return 26;
                    case 116: return 26;
                    case 117: return 52;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 26;
                    case 121: return 26;
                    case 122: return 26;
                    default: return -1;
                }
            case 31:
                switch (c)
                {
                    case 48: return 26;
                    case 49: return 26;
                    case 50: return 26;
                    case 51: return 26;
                    case 52: return 26;
                    case 53: return 26;
                    case 54: return 26;
                    case 55: return 26;
                    case 56: return 26;
                    case 57: return 26;
                    case 65: return 26;
                    case 66: return 26;
                    case 67: return 26;
                    case 68: return 26;
                    case 69: return 26;
                    case 70: return 26;
                    case 71: return 26;
                    case 72: return 26;
                    case 73: return 26;
                    case 74: return 26;
                    case 75: return 26;
                    case 76: return 26;
                    case 77: return 26;
                    case 78: return 26;
                    case 79: return 26;
                    case 80: return 26;
                    case 81: return 26;
                    case 82: return 26;
                    case 83: return 26;
                    case 84: return 26;
                    case 85: return 26;
                    case 86: return 26;
                    case 87: return 26;
                    case 88: return 26;
                    case 89: return 26;
                    case 90: return 26;
                    case 95: return 26;
                    case 97: return 26;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 26;
                    case 101: return 26;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 26;
                    case 111: return 53;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 26;
                    case 115: return 26;
                    case 116: return 26;
                    case 117: return 26;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 26;
                    case 121: return 26;
                    case 122: return 26;
                    default: return -1;
                }
            case 33:
                switch (c)
                {
                    case 124: return 54;
                    default: return -1;
                }
            case 40:
                switch (c)
                {
                    case 9: return 40;
                    case 10: return 40;
                    case 13: return 40;
                    case 32: return 40;
                    case 33: return 40;
                    case 34: return 40;
                    case 35: return 40;
                    case 36: return 40;
                    case 37: return 40;
                    case 38: return 40;
                    case 39: return 40;
                    case 40: return 40;
                    case 41: return 40;
                    case 42: return 55;
                    case 43: return 40;
                    case 44: return 40;
                    case 45: return 40;
                    case 46: return 40;
                    case 47: return 40;
                    case 48: return 40;
                    case 49: return 40;
                    case 50: return 40;
                    case 51: return 40;
                    case 52: return 40;
                    case 53: return 40;
                    case 54: return 40;
                    case 55: return 40;
                    case 56: return 40;
                    case 57: return 40;
                    case 58: return 40;
                    case 59: return 40;
                    case 60: return 40;
                    case 61: return 40;
                    case 62: return 40;
                    case 63: return 40;
                    case 64: return 40;
                    case 65: return 40;
                    case 66: return 40;
                    case 67: return 40;
                    case 68: return 40;
                    case 69: return 40;
                    case 70: return 40;
                    case 71: return 40;
                    case 72: return 40;
                    case 73: return 40;
                    case 74: return 40;
                    case 75: return 40;
                    case 76: return 40;
                    case 77: return 40;
                    case 78: return 40;
                    case 79: return 40;
                    case 80: return 40;
                    case 81: return 40;
                    case 82: return 40;
                    case 83: return 40;
                    case 84: return 40;
                    case 85: return 40;
                    case 86: return 40;
                    case 87: return 40;
                    case 88: return 40;
                    case 89: return 40;
                    case 90: return 40;
                    case 91: return 40;
                    case 92: return 40;
                    case 93: return 40;
                    case 94: return 40;
                    case 95: return 40;
                    case 96: return 40;
                    case 97: return 40;
                    case 98: return 40;
                    case 99: return 40;
                    case 100: return 40;
                    case 101: return 40;
                    case 102: return 40;
                    case 103: return 40;
                    case 104: return 40;
                    case 105: return 40;
                    case 106: return 40;
                    case 107: return 40;
                    case 108: return 40;
                    case 109: return 40;
                    case 110: return 40;
                    case 111: return 40;
                    case 112: return 40;
                    case 113: return 40;
                    case 114: return 40;
                    case 115: return 40;
                    case 116: return 40;
                    case 117: return 40;
                    case 118: return 40;
                    case 119: return 40;
                    case 120: return 40;
                    case 121: return 40;
                    case 122: return 40;
                    case 123: return 40;
                    case 124: return 40;
                    case 125: return 40;
                    case 126: return 40;
                    case 161: return 40;
                    case 162: return 40;
                    case 163: return 40;
                    case 164: return 40;
                    case 165: return 40;
                    case 166: return 40;
                    case 167: return 40;
                    case 168: return 40;
                    case 169: return 40;
                    case 170: return 40;
                    case 171: return 40;
                    case 172: return 40;
                    case 173: return 40;
                    case 174: return 40;
                    case 175: return 40;
                    case 176: return 40;
                    case 177: return 40;
                    case 178: return 40;
                    case 179: return 40;
                    case 180: return 40;
                    case 181: return 40;
                    case 182: return 40;
                    case 183: return 40;
                    case 184: return 40;
                    case 185: return 40;
                    case 186: return 40;
                    case 187: return 40;
                    case 188: return 40;
                    case 189: return 40;
                    case 190: return 40;
                    case 191: return 40;
                    case 192: return 40;
                    case 193: return 40;
                    case 194: return 40;
                    case 195: return 40;
                    case 196: return 40;
                    case 197: return 40;
                    case 198: return 40;
                    case 199: return 40;
                    case 200: return 40;
                    case 201: return 40;
                    case 202: return 40;
                    case 203: return 40;
                    case 204: return 40;
                    case 205: return 40;
                    case 206: return 40;
                    case 207: return 40;
                    case 208: return 40;
                    case 209: return 40;
                    case 210: return 40;
                    case 211: return 40;
                    case 212: return 40;
                    case 213: return 40;
                    case 214: return 40;
                    case 215: return 40;
                    case 216: return 40;
                    case 217: return 40;
                    case 218: return 40;
                    case 219: return 40;
                    case 220: return 40;
                    case 221: return 40;
                    case 222: return 40;
                    case 223: return 40;
                    case 224: return 40;
                    case 225: return 40;
                    case 226: return 40;
                    case 227: return 40;
                    case 228: return 40;
                    case 229: return 40;
                    case 230: return 40;
                    case 231: return 40;
                    case 232: return 40;
                    case 233: return 40;
                    case 234: return 40;
                    case 235: return 40;
                    case 236: return 40;
                    case 237: return 40;
                    case 238: return 40;
                    case 239: return 40;
                    case 240: return 40;
                    case 241: return 40;
                    case 242: return 40;
                    case 243: return 40;
                    case 244: return 40;
                    case 245: return 40;
                    case 246: return 40;
                    case 247: return 40;
                    case 248: return 40;
                    case 249: return 40;
                    case 250: return 40;
                    case 251: return 40;
                    case 252: return 40;
                    case 253: return 40;
                    case 254: return 40;
                    case 255: return 40;
                    default: return -1;
                }
            case 41:
                switch (c)
                {
                    case 9: return 56;
                    case 32: return 56;
                    case 33: return 56;
                    case 34: return 56;
                    case 35: return 56;
                    case 36: return 56;
                    case 37: return 56;
                    case 38: return 56;
                    case 39: return 56;
                    case 40: return 56;
                    case 41: return 56;
                    case 42: return 56;
                    case 43: return 56;
                    case 44: return 56;
                    case 45: return 56;
                    case 46: return 56;
                    case 47: return 41;
                    case 48: return 56;
                    case 49: return 56;
                    case 50: return 56;
                    case 51: return 56;
                    case 52: return 56;
                    case 53: return 56;
                    case 54: return 56;
                    case 55: return 56;
                    case 56: return 56;
                    case 57: return 56;
                    case 58: return 56;
                    case 59: return 56;
                    case 60: return 56;
                    case 61: return 56;
                    case 62: return 56;
                    case 63: return 56;
                    case 64: return 56;
                    case 65: return 56;
                    case 66: return 56;
                    case 67: return 56;
                    case 68: return 56;
                    case 69: return 56;
                    case 70: return 56;
                    case 71: return 56;
                    case 72: return 56;
                    case 73: return 56;
                    case 74: return 56;
                    case 75: return 56;
                    case 76: return 56;
                    case 77: return 56;
                    case 78: return 56;
                    case 79: return 56;
                    case 80: return 56;
                    case 81: return 56;
                    case 82: return 56;
                    case 83: return 56;
                    case 84: return 56;
                    case 85: return 56;
                    case 86: return 56;
                    case 87: return 56;
                    case 88: return 56;
                    case 89: return 56;
                    case 90: return 56;
                    case 91: return 56;
                    case 92: return 56;
                    case 93: return 56;
                    case 94: return 56;
                    case 95: return 56;
                    case 96: return 56;
                    case 97: return 56;
                    case 98: return 56;
                    case 99: return 56;
                    case 100: return 56;
                    case 101: return 56;
                    case 102: return 56;
                    case 103: return 56;
                    case 104: return 56;
                    case 105: return 56;
                    case 106: return 56;
                    case 107: return 56;
                    case 108: return 56;
                    case 109: return 56;
                    case 110: return 56;
                    case 111: return 56;
                    case 112: return 56;
                    case 113: return 56;
                    case 114: return 56;
                    case 115: return 56;
                    case 116: return 56;
                    case 117: return 56;
                    case 118: return 56;
                    case 119: return 56;
                    case 120: return 56;
                    case 121: return 56;
                    case 122: return 56;
                    case 123: return 56;
                    case 124: return 56;
                    case 125: return 56;
                    case 126: return 56;
                    case 161: return 56;
                    case 162: return 56;
                    case 163: return 56;
                    case 164: return 56;
                    case 165: return 56;
                    case 166: return 56;
                    case 167: return 56;
                    case 168: return 56;
                    case 169: return 56;
                    case 170: return 56;
                    case 171: return 56;
                    case 172: return 56;
                    case 173: return 56;
                    case 174: return 56;
                    case 175: return 56;
                    case 176: return 56;
                    case 177: return 56;
                    case 178: return 56;
                    case 179: return 56;
                    case 180: return 56;
                    case 181: return 56;
                    case 182: return 56;
                    case 183: return 56;
                    case 184: return 56;
                    case 185: return 56;
                    case 186: return 56;
                    case 187: return 56;
                    case 188: return 56;
                    case 189: return 56;
                    case 190: return 56;
                    case 191: return 56;
                    case 192: return 56;
                    case 193: return 56;
                    case 194: return 56;
                    case 195: return 56;
                    case 196: return 56;
                    case 197: return 56;
                    case 198: return 56;
                    case 199: return 56;
                    case 200: return 56;
                    case 201: return 56;
                    case 202: return 56;
                    case 203: return 56;
                    case 204: return 56;
                    case 205: return 56;
                    case 206: return 56;
                    case 207: return 56;
                    case 208: return 56;
                    case 209: return 56;
                    case 210: return 56;
                    case 211: return 56;
                    case 212: return 56;
                    case 213: return 56;
                    case 214: return 56;
                    case 215: return 56;
                    case 216: return 56;
                    case 217: return 56;
                    case 218: return 56;
                    case 219: return 56;
                    case 220: return 56;
                    case 221: return 56;
                    case 222: return 56;
                    case 223: return 56;
                    case 224: return 56;
                    case 225: return 56;
                    case 226: return 56;
                    case 227: return 56;
                    case 228: return 56;
                    case 229: return 56;
                    case 230: return 56;
                    case 231: return 56;
                    case 232: return 56;
                    case 233: return 56;
                    case 234: return 56;
                    case 235: return 56;
                    case 236: return 56;
                    case 237: return 56;
                    case 238: return 56;
                    case 239: return 56;
                    case 240: return 56;
                    case 241: return 56;
                    case 242: return 56;
                    case 243: return 56;
                    case 244: return 56;
                    case 245: return 56;
                    case 246: return 56;
                    case 247: return 56;
                    case 248: return 56;
                    case 249: return 56;
                    case 250: return 56;
                    case 251: return 56;
                    case 252: return 56;
                    case 253: return 56;
                    case 254: return 56;
                    case 255: return 56;
                    default: return -1;
                }
            case 42:
                switch (c)
                {
                    case 48: return 57;
                    case 49: return 57;
                    case 50: return 57;
                    case 51: return 57;
                    case 52: return 57;
                    case 53: return 57;
                    case 54: return 57;
                    case 55: return 57;
                    case 56: return 57;
                    case 57: return 57;
                    default: return -1;
                }
            case 45:
                switch (c)
                {
                    case 61: return 58;
                    default: return -1;
                }
            case 47:
                switch (c)
                {
                    case 101: return 59;
                    default: return -1;
                }
            case 48:
                switch (c)
                {
                    case 48: return 26;
                    case 49: return 26;
                    case 50: return 26;
                    case 51: return 26;
                    case 52: return 26;
                    case 53: return 26;
                    case 54: return 26;
                    case 55: return 26;
                    case 56: return 26;
                    case 57: return 26;
                    case 65: return 26;
                    case 66: return 26;
                    case 67: return 26;
                    case 68: return 26;
                    case 69: return 26;
                    case 70: return 26;
                    case 71: return 26;
                    case 72: return 26;
                    case 73: return 26;
                    case 74: return 26;
                    case 75: return 26;
                    case 76: return 26;
                    case 77: return 26;
                    case 78: return 26;
                    case 79: return 26;
                    case 80: return 26;
                    case 81: return 26;
                    case 82: return 26;
                    case 83: return 26;
                    case 84: return 26;
                    case 85: return 26;
                    case 86: return 26;
                    case 87: return 26;
                    case 88: return 26;
                    case 89: return 26;
                    case 90: return 26;
                    case 95: return 26;
                    case 97: return 26;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 60;
                    case 101: return 26;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 26;
                    case 111: return 26;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 26;
                    case 115: return 26;
                    case 116: return 26;
                    case 117: return 26;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 26;
                    case 121: return 26;
                    case 122: return 26;
                    default: return -1;
                }
            case 49:
                switch (c)
                {
                    case 48: return 26;
                    case 49: return 26;
                    case 50: return 26;
                    case 51: return 26;
                    case 52: return 26;
                    case 53: return 26;
                    case 54: return 26;
                    case 55: return 26;
                    case 56: return 26;
                    case 57: return 26;
                    case 65: return 26;
                    case 66: return 26;
                    case 67: return 26;
                    case 68: return 26;
                    case 69: return 26;
                    case 70: return 26;
                    case 71: return 26;
                    case 72: return 26;
                    case 73: return 26;
                    case 74: return 26;
                    case 75: return 26;
                    case 76: return 26;
                    case 77: return 26;
                    case 78: return 26;
                    case 79: return 26;
                    case 80: return 26;
                    case 81: return 26;
                    case 82: return 26;
                    case 83: return 26;
                    case 84: return 26;
                    case 85: return 26;
                    case 86: return 26;
                    case 87: return 26;
                    case 88: return 26;
                    case 89: return 26;
                    case 90: return 26;
                    case 95: return 26;
                    case 97: return 26;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 26;
                    case 101: return 26;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 26;
                    case 111: return 61;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 26;
                    case 115: return 26;
                    case 116: return 26;
                    case 117: return 26;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 26;
                    case 121: return 26;
                    case 122: return 26;
                    default: return -1;
                }
            case 50:
                switch (c)
                {
                    case 48: return 26;
                    case 49: return 26;
                    case 50: return 26;
                    case 51: return 26;
                    case 52: return 26;
                    case 53: return 26;
                    case 54: return 26;
                    case 55: return 26;
                    case 56: return 26;
                    case 57: return 26;
                    case 65: return 26;
                    case 66: return 26;
                    case 67: return 26;
                    case 68: return 26;
                    case 69: return 26;
                    case 70: return 26;
                    case 71: return 26;
                    case 72: return 26;
                    case 73: return 26;
                    case 74: return 26;
                    case 75: return 26;
                    case 76: return 26;
                    case 77: return 26;
                    case 78: return 26;
                    case 79: return 26;
                    case 80: return 26;
                    case 81: return 26;
                    case 82: return 26;
                    case 83: return 26;
                    case 84: return 26;
                    case 85: return 26;
                    case 86: return 26;
                    case 87: return 26;
                    case 88: return 26;
                    case 89: return 26;
                    case 90: return 26;
                    case 95: return 26;
                    case 97: return 26;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 26;
                    case 101: return 26;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 26;
                    case 111: return 26;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 26;
                    case 115: return 26;
                    case 116: return 62;
                    case 117: return 26;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 26;
                    case 121: return 26;
                    case 122: return 26;
                    default: return -1;
                }
            case 51:
                switch (c)
                {
                    case 48: return 26;
                    case 49: return 26;
                    case 50: return 26;
                    case 51: return 26;
                    case 52: return 26;
                    case 53: return 26;
                    case 54: return 26;
                    case 55: return 26;
                    case 56: return 26;
                    case 57: return 26;
                    case 65: return 26;
                    case 66: return 26;
                    case 67: return 26;
                    case 68: return 26;
                    case 69: return 26;
                    case 70: return 26;
                    case 71: return 26;
                    case 72: return 26;
                    case 73: return 26;
                    case 74: return 26;
                    case 75: return 26;
                    case 76: return 26;
                    case 77: return 26;
                    case 78: return 26;
                    case 79: return 26;
                    case 80: return 26;
                    case 81: return 26;
                    case 82: return 26;
                    case 83: return 26;
                    case 84: return 26;
                    case 85: return 26;
                    case 86: return 26;
                    case 87: return 26;
                    case 88: return 26;
                    case 89: return 26;
                    case 90: return 26;
                    case 95: return 26;
                    case 97: return 26;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 26;
                    case 101: return 26;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 26;
                    case 111: return 26;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 26;
                    case 115: return 26;
                    case 116: return 26;
                    case 117: return 26;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 26;
                    case 121: return 26;
                    case 122: return 26;
                    default: return -1;
                }
            case 52:
                switch (c)
                {
                    case 48: return 26;
                    case 49: return 26;
                    case 50: return 26;
                    case 51: return 26;
                    case 52: return 26;
                    case 53: return 26;
                    case 54: return 26;
                    case 55: return 26;
                    case 56: return 26;
                    case 57: return 26;
                    case 65: return 26;
                    case 66: return 26;
                    case 67: return 26;
                    case 68: return 26;
                    case 69: return 26;
                    case 70: return 26;
                    case 71: return 26;
                    case 72: return 26;
                    case 73: return 26;
                    case 74: return 26;
                    case 75: return 26;
                    case 76: return 26;
                    case 77: return 26;
                    case 78: return 26;
                    case 79: return 26;
                    case 80: return 26;
                    case 81: return 26;
                    case 82: return 26;
                    case 83: return 26;
                    case 84: return 26;
                    case 85: return 26;
                    case 86: return 26;
                    case 87: return 26;
                    case 88: return 26;
                    case 89: return 26;
                    case 90: return 26;
                    case 95: return 26;
                    case 97: return 26;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 26;
                    case 101: return 26;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 26;
                    case 111: return 26;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 26;
                    case 115: return 26;
                    case 116: return 26;
                    case 117: return 26;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 26;
                    case 121: return 26;
                    case 122: return 26;
                    default: return -1;
                }
            case 53:
                switch (c)
                {
                    case 48: return 26;
                    case 49: return 26;
                    case 50: return 26;
                    case 51: return 26;
                    case 52: return 26;
                    case 53: return 26;
                    case 54: return 26;
                    case 55: return 26;
                    case 56: return 26;
                    case 57: return 26;
                    case 65: return 26;
                    case 66: return 26;
                    case 67: return 26;
                    case 68: return 26;
                    case 69: return 26;
                    case 70: return 26;
                    case 71: return 26;
                    case 72: return 26;
                    case 73: return 26;
                    case 74: return 26;
                    case 75: return 26;
                    case 76: return 26;
                    case 77: return 26;
                    case 78: return 26;
                    case 79: return 26;
                    case 80: return 26;
                    case 81: return 26;
                    case 82: return 26;
                    case 83: return 26;
                    case 84: return 26;
                    case 85: return 26;
                    case 86: return 26;
                    case 87: return 26;
                    case 88: return 26;
                    case 89: return 26;
                    case 90: return 26;
                    case 95: return 26;
                    case 97: return 26;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 26;
                    case 101: return 26;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 26;
                    case 111: return 26;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 63;
                    case 115: return 26;
                    case 116: return 26;
                    case 117: return 26;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 26;
                    case 121: return 26;
                    case 122: return 26;
                    default: return -1;
                }
            case 55:
                switch (c)
                {
                    case 47: return 64;
                    default: return -1;
                }
            case 56:
                switch (c)
                {
                    case 9: return 56;
                    case 32: return 56;
                    case 33: return 56;
                    case 34: return 56;
                    case 35: return 56;
                    case 36: return 56;
                    case 37: return 56;
                    case 38: return 56;
                    case 39: return 56;
                    case 40: return 56;
                    case 41: return 56;
                    case 42: return 56;
                    case 43: return 56;
                    case 44: return 56;
                    case 45: return 56;
                    case 46: return 56;
                    case 47: return 56;
                    case 48: return 56;
                    case 49: return 56;
                    case 50: return 56;
                    case 51: return 56;
                    case 52: return 56;
                    case 53: return 56;
                    case 54: return 56;
                    case 55: return 56;
                    case 56: return 56;
                    case 57: return 56;
                    case 58: return 56;
                    case 59: return 56;
                    case 60: return 56;
                    case 61: return 56;
                    case 62: return 56;
                    case 63: return 56;
                    case 64: return 56;
                    case 65: return 56;
                    case 66: return 56;
                    case 67: return 56;
                    case 68: return 56;
                    case 69: return 56;
                    case 70: return 56;
                    case 71: return 56;
                    case 72: return 56;
                    case 73: return 56;
                    case 74: return 56;
                    case 75: return 56;
                    case 76: return 56;
                    case 77: return 56;
                    case 78: return 56;
                    case 79: return 56;
                    case 80: return 56;
                    case 81: return 56;
                    case 82: return 56;
                    case 83: return 56;
                    case 84: return 56;
                    case 85: return 56;
                    case 86: return 56;
                    case 87: return 56;
                    case 88: return 56;
                    case 89: return 56;
                    case 90: return 56;
                    case 91: return 56;
                    case 92: return 56;
                    case 93: return 56;
                    case 94: return 56;
                    case 95: return 56;
                    case 96: return 56;
                    case 97: return 56;
                    case 98: return 56;
                    case 99: return 56;
                    case 100: return 56;
                    case 101: return 56;
                    case 102: return 56;
                    case 103: return 56;
                    case 104: return 56;
                    case 105: return 56;
                    case 106: return 56;
                    case 107: return 56;
                    case 108: return 56;
                    case 109: return 56;
                    case 110: return 56;
                    case 111: return 56;
                    case 112: return 56;
                    case 113: return 56;
                    case 114: return 56;
                    case 115: return 56;
                    case 116: return 56;
                    case 117: return 56;
                    case 118: return 56;
                    case 119: return 56;
                    case 120: return 56;
                    case 121: return 56;
                    case 122: return 56;
                    case 123: return 56;
                    case 124: return 56;
                    case 125: return 56;
                    case 126: return 56;
                    case 161: return 56;
                    case 162: return 56;
                    case 163: return 56;
                    case 164: return 56;
                    case 165: return 56;
                    case 166: return 56;
                    case 167: return 56;
                    case 168: return 56;
                    case 169: return 56;
                    case 170: return 56;
                    case 171: return 56;
                    case 172: return 56;
                    case 173: return 56;
                    case 174: return 56;
                    case 175: return 56;
                    case 176: return 56;
                    case 177: return 56;
                    case 178: return 56;
                    case 179: return 56;
                    case 180: return 56;
                    case 181: return 56;
                    case 182: return 56;
                    case 183: return 56;
                    case 184: return 56;
                    case 185: return 56;
                    case 186: return 56;
                    case 187: return 56;
                    case 188: return 56;
                    case 189: return 56;
                    case 190: return 56;
                    case 191: return 56;
                    case 192: return 56;
                    case 193: return 56;
                    case 194: return 56;
                    case 195: return 56;
                    case 196: return 56;
                    case 197: return 56;
                    case 198: return 56;
                    case 199: return 56;
                    case 200: return 56;
                    case 201: return 56;
                    case 202: return 56;
                    case 203: return 56;
                    case 204: return 56;
                    case 205: return 56;
                    case 206: return 56;
                    case 207: return 56;
                    case 208: return 56;
                    case 209: return 56;
                    case 210: return 56;
                    case 211: return 56;
                    case 212: return 56;
                    case 213: return 56;
                    case 214: return 56;
                    case 215: return 56;
                    case 216: return 56;
                    case 217: return 56;
                    case 218: return 56;
                    case 219: return 56;
                    case 220: return 56;
                    case 221: return 56;
                    case 222: return 56;
                    case 223: return 56;
                    case 224: return 56;
                    case 225: return 56;
                    case 226: return 56;
                    case 227: return 56;
                    case 228: return 56;
                    case 229: return 56;
                    case 230: return 56;
                    case 231: return 56;
                    case 232: return 56;
                    case 233: return 56;
                    case 234: return 56;
                    case 235: return 56;
                    case 236: return 56;
                    case 237: return 56;
                    case 238: return 56;
                    case 239: return 56;
                    case 240: return 56;
                    case 241: return 56;
                    case 242: return 56;
                    case 243: return 56;
                    case 244: return 56;
                    case 245: return 56;
                    case 246: return 56;
                    case 247: return 56;
                    case 248: return 56;
                    case 249: return 56;
                    case 250: return 56;
                    case 251: return 56;
                    case 252: return 56;
                    case 253: return 56;
                    case 254: return 56;
                    case 255: return 56;
                    default: return -1;
                }
            case 57:
                switch (c)
                {
                    case 48: return 57;
                    case 49: return 57;
                    case 50: return 57;
                    case 51: return 57;
                    case 52: return 57;
                    case 53: return 57;
                    case 54: return 57;
                    case 55: return 57;
                    case 56: return 57;
                    case 57: return 57;
                    default: return -1;
                }
            case 59:
                switch (c)
                {
                    case 114: return 65;
                    default: return -1;
                }
            case 60:
                switch (c)
                {
                    case 48: return 26;
                    case 49: return 26;
                    case 50: return 26;
                    case 51: return 26;
                    case 52: return 26;
                    case 53: return 26;
                    case 54: return 26;
                    case 55: return 26;
                    case 56: return 26;
                    case 57: return 26;
                    case 65: return 26;
                    case 66: return 26;
                    case 67: return 26;
                    case 68: return 26;
                    case 69: return 26;
                    case 70: return 26;
                    case 71: return 26;
                    case 72: return 26;
                    case 73: return 26;
                    case 74: return 26;
                    case 75: return 26;
                    case 76: return 26;
                    case 77: return 26;
                    case 78: return 26;
                    case 79: return 26;
                    case 80: return 26;
                    case 81: return 26;
                    case 82: return 26;
                    case 83: return 26;
                    case 84: return 26;
                    case 85: return 26;
                    case 86: return 26;
                    case 87: return 26;
                    case 88: return 26;
                    case 89: return 26;
                    case 90: return 26;
                    case 95: return 26;
                    case 97: return 26;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 26;
                    case 101: return 26;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 26;
                    case 111: return 26;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 26;
                    case 115: return 26;
                    case 116: return 26;
                    case 117: return 26;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 26;
                    case 121: return 26;
                    case 122: return 26;
                    default: return -1;
                }
            case 61:
                switch (c)
                {
                    case 48: return 26;
                    case 49: return 26;
                    case 50: return 26;
                    case 51: return 26;
                    case 52: return 26;
                    case 53: return 26;
                    case 54: return 26;
                    case 55: return 26;
                    case 56: return 26;
                    case 57: return 26;
                    case 65: return 26;
                    case 66: return 26;
                    case 67: return 26;
                    case 68: return 26;
                    case 69: return 26;
                    case 70: return 26;
                    case 71: return 26;
                    case 72: return 26;
                    case 73: return 26;
                    case 74: return 26;
                    case 75: return 26;
                    case 76: return 26;
                    case 77: return 26;
                    case 78: return 26;
                    case 79: return 26;
                    case 80: return 26;
                    case 81: return 26;
                    case 82: return 26;
                    case 83: return 26;
                    case 84: return 26;
                    case 85: return 26;
                    case 86: return 26;
                    case 87: return 26;
                    case 88: return 26;
                    case 89: return 26;
                    case 90: return 26;
                    case 95: return 26;
                    case 97: return 26;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 26;
                    case 101: return 26;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 26;
                    case 111: return 26;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 26;
                    case 115: return 26;
                    case 116: return 26;
                    case 117: return 26;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 26;
                    case 121: return 26;
                    case 122: return 26;
                    default: return -1;
                }
            case 62:
                switch (c)
                {
                    case 48: return 26;
                    case 49: return 26;
                    case 50: return 26;
                    case 51: return 26;
                    case 52: return 26;
                    case 53: return 26;
                    case 54: return 26;
                    case 55: return 26;
                    case 56: return 26;
                    case 57: return 26;
                    case 65: return 26;
                    case 66: return 26;
                    case 67: return 26;
                    case 68: return 26;
                    case 69: return 26;
                    case 70: return 26;
                    case 71: return 26;
                    case 72: return 26;
                    case 73: return 26;
                    case 74: return 26;
                    case 75: return 26;
                    case 76: return 26;
                    case 77: return 26;
                    case 78: return 26;
                    case 79: return 26;
                    case 80: return 26;
                    case 81: return 26;
                    case 82: return 26;
                    case 83: return 26;
                    case 84: return 26;
                    case 85: return 26;
                    case 86: return 26;
                    case 87: return 26;
                    case 88: return 26;
                    case 89: return 26;
                    case 90: return 26;
                    case 95: return 26;
                    case 97: return 26;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 26;
                    case 101: return 26;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 26;
                    case 111: return 26;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 26;
                    case 115: return 26;
                    case 116: return 26;
                    case 117: return 26;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 26;
                    case 121: return 26;
                    case 122: return 26;
                    default: return -1;
                }
            case 63:
                switch (c)
                {
                    case 48: return 26;
                    case 49: return 26;
                    case 50: return 26;
                    case 51: return 26;
                    case 52: return 26;
                    case 53: return 26;
                    case 54: return 26;
                    case 55: return 26;
                    case 56: return 26;
                    case 57: return 26;
                    case 65: return 26;
                    case 66: return 26;
                    case 67: return 26;
                    case 68: return 26;
                    case 69: return 26;
                    case 70: return 26;
                    case 71: return 26;
                    case 72: return 26;
                    case 73: return 26;
                    case 74: return 26;
                    case 75: return 26;
                    case 76: return 26;
                    case 77: return 26;
                    case 78: return 26;
                    case 79: return 26;
                    case 80: return 26;
                    case 81: return 26;
                    case 82: return 26;
                    case 83: return 26;
                    case 84: return 26;
                    case 85: return 26;
                    case 86: return 26;
                    case 87: return 26;
                    case 88: return 26;
                    case 89: return 26;
                    case 90: return 26;
                    case 95: return 26;
                    case 97: return 26;
                    case 98: return 26;
                    case 99: return 26;
                    case 100: return 26;
                    case 101: return 26;
                    case 102: return 26;
                    case 103: return 26;
                    case 104: return 26;
                    case 105: return 26;
                    case 106: return 26;
                    case 107: return 26;
                    case 108: return 26;
                    case 109: return 26;
                    case 110: return 26;
                    case 111: return 26;
                    case 112: return 26;
                    case 113: return 26;
                    case 114: return 26;
                    case 115: return 26;
                    case 116: return 26;
                    case 117: return 26;
                    case 118: return 26;
                    case 119: return 26;
                    case 120: return 26;
                    case 121: return 26;
                    case 122: return 26;
                    default: return -1;
                }
            case 65:
                switch (c)
                {
                    case 115: return 66;
                    default: return -1;
                }
            case 66:
                switch (c)
                {
                    case 58: return 67;
                    default: return -1;
                }
            default: return -1;
        }
    }

    private int tokenForState(int state)
    {
        if (state < 0 || state >= TOKEN_STATE.length)
            return -1;

        return TOKEN_STATE[state];
    }

    public int lookupToken(int base, String key)
    {
        int start = SPECIAL_CASES_INDEXES[base];
        int end   = SPECIAL_CASES_INDEXES[base+1]-1;

        while (start <= end)
        {
            int half = (start+end)/2;
            int comp = SPECIAL_CASES_KEYS[half].compareTo(key);

            if (comp == 0)
                return SPECIAL_CASES_VALUES[half];
            else if (comp < 0)
                start = half+1;
            else  //(comp > 0)
                end = half-1;
        }

        return base;
    }

    private boolean hasInput()
    {
        return position < input.length();
    }

    private char nextChar()
    {
        if (hasInput())
            return input.charAt(position++);
        else
            return (char) -1;
    }
}
