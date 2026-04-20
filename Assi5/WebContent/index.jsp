<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Registration</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Serif+Display:ital@0;1&family=DM+Sans:opsz,wght@9..40,300;9..40,400;9..40,500;9..40,600;9..40,700&display=swap" rel="stylesheet">
    <style>
        :root {
            --bg: #0f0e17;
            --surface: #1a1928;
            --surface2: #22203a;
            --accent: #e8c547;
            --text: #fffffe;
            --muted: #a7a9be;
            --border: #2e2c4a;
            --success: #4ecca3;
            --error: #ff6b6b;
            --radius: 12px;
        }
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body {
            font-family: 'DM Sans', sans-serif;
            background: var(--bg);
            color: var(--text);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        .bg-blobs { position: fixed; inset: 0; z-index: 0; pointer-events: none; }
        .blob { position: absolute; border-radius: 50%; filter: blur(90px); opacity: 0.18; animation: float 12s ease-in-out infinite; }
        .blob-1 { width: 500px; height: 500px; background: #6c5ce7; top: -150px; left: -100px; }
        .blob-2 { width: 400px; height: 400px; background: #e8c547; bottom: -100px; right: -80px; animation-delay: -4s; }
        .blob-3 { width: 300px; height: 300px; background: #ff6b6b; bottom: 100px; left: 40%; animation-delay: -8s; }
        @keyframes float { 0%,100%{transform:translateY(0) scale(1);} 50%{transform:translateY(-30px) scale(1.05);} }
        .card {
            position: relative; z-index: 1;
            width: 100%; max-width: 520px;
            background: var(--surface);
            border: 1px solid var(--border);
            border-radius: 20px;
            padding: 40px 44px;
            box-shadow: 0 30px 80px rgba(0,0,0,0.5);
            animation: slideUp 0.6s cubic-bezier(0.16,1,0.3,1) both;
        }
        @keyframes slideUp { from{opacity:0;transform:translateY(40px);} to{opacity:1;transform:translateY(0);} }
        .badge {
            display: inline-block;
            background: rgba(232,197,71,0.12);
            color: var(--accent);
            font-size: 11px; font-weight: 600;
            letter-spacing: 0.12em; text-transform: uppercase;
            padding: 4px 12px; border-radius: 100px;
            border: 1px solid rgba(232,197,71,0.25);
            margin-bottom: 12px;
        }
        h1 { font-family: 'DM Serif Display', serif; font-size: 2rem; line-height: 1.15; }
        h1 em { font-style: italic; color: var(--accent); }
        .subtitle { margin-top: 6px; font-size: 13.5px; color: var(--muted); }
        .header { margin-bottom: 28px; }
        .row-2 { display: grid; grid-template-columns: 1fr 1fr; gap: 14px; }
        .field { display: flex; flex-direction: column; gap: 6px; margin-bottom: 14px; }
        label { font-size: 11.5px; font-weight: 600; letter-spacing: 0.06em; text-transform: uppercase; color: var(--muted); }
        .input-wrap { position: relative; }
        .input-icon { position: absolute; left: 13px; top: 50%; transform: translateY(-50%); opacity: 0.45; font-size: 14px; pointer-events: none; }
        input, select {
            width: 100%; padding: 11px 12px 11px 38px;
            background: var(--surface2);
            border: 1.5px solid var(--border);
            border-radius: var(--radius);
            color: var(--text);
            font-family: 'DM Sans', sans-serif; font-size: 14px;
            transition: border-color 0.2s, box-shadow 0.2s;
            appearance: none; -webkit-appearance: none;
        }
        input::placeholder { color: #4a4869; }
        input:focus, select:focus { outline: none; border-color: var(--accent); box-shadow: 0 0 0 3px rgba(232,197,71,0.12); }
        input.invalid { border-color: var(--error); }
        input.valid, select.valid { border-color: var(--success); }
        .select-wrap { position: relative; }
        .select-wrap::after { content: '▾'; position: absolute; right: 13px; top: 50%; transform: translateY(-50%); color: var(--muted); pointer-events: none; }
        select { padding-right: 36px; }
        select option { background: var(--surface2); }
        .field-hint { font-size: 11px; color: var(--muted); opacity: 0.7; }
        .field-error { font-size: 11px; color: var(--error); display: none; }
        .field-error.show { display: block; }
        .error-banner {
            background: rgba(255,107,107,0.1);
            border: 1px solid rgba(255,107,107,0.3);
            border-radius: var(--radius);
            padding: 12px 16px; color: var(--error);
            font-size: 13.5px; margin-bottom: 20px;
        }
        .btn {
            width: 100%; margin-top: 22px; padding: 14px;
            background: var(--accent); color: #0f0e17;
            font-family: 'DM Sans', sans-serif; font-size: 15px; font-weight: 700;
            border: none; border-radius: var(--radius); cursor: pointer;
            transition: transform 0.15s, box-shadow 0.15s;
            box-shadow: 0 4px 20px rgba(232,197,71,0.3);
        }
        .btn:hover { background: #f0d050; transform: translateY(-1px); box-shadow: 0 8px 28px rgba(232,197,71,0.4); }
        .btn:active { transform: translateY(0); }
        .card-footer { margin-top: 16px; text-align: center; font-size: 12px; color: var(--muted); }
        .card-footer span { color: var(--accent); }
    </style>
</head>
<body>
<div class="bg-blobs">
    <div class="blob blob-1"></div>
    <div class="blob blob-2"></div>
    <div class="blob blob-3"></div>
</div>
<div class="card">
    <div class="header">
        <div class="badge">HR Portal</div>
        <h1>Register <em>Employee</em></h1>
        <p class="subtitle">Fill in the details to onboard a new team member.</p>
    </div>

    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
    <div class="error-banner">⚠&nbsp; <%= error %></div>
    <% } %>

    <form id="regForm" action="employee" method="post" novalidate>
        <div class="row-2">
            <div class="field">
                <label for="id">Employee ID</label>
                <div class="input-wrap">
                    <span class="input-icon">#</span>
                    <input type="number" id="id" name="id" placeholder="1 – 20" min="1" max="20" required>
                </div>
                <span class="field-error" id="id-err">Must be 1–20.</span>
            </div>
            <div class="field">
                <label for="department">Department</label>
                <div class="input-wrap select-wrap">
                    <span class="input-icon">🏢</span>
                    <select id="department" name="department" required>
                        <option value="" disabled selected>Select…</option>
                        <option>Engineering</option>
                        <option>Marketing</option>
                        <option>Human Resources</option>
                        <option>Finance</option>
                        <option>Operations</option>
                        <option>Sales</option>
                    </select>
                </div>
                <span class="field-error" id="dept-err">Select a department.</span>
            </div>
        </div>

        <div class="field">
            <label for="name">Full Name</label>
            <div class="input-wrap">
                <span class="input-icon">👤</span>
                <input type="text" id="name" name="name" placeholder="Jane Smith" required>
            </div>
            <span class="field-error" id="name-err">Name is required.</span>
        </div>

        <div class="field">
            <label for="email">Email Address</label>
            <div class="input-wrap">
                <span class="input-icon">✉</span>
                <input type="text" id="email" name="email" placeholder="jane@company.com" required>
            </div>
            <span class="field-error" id="email-err">Enter a valid email.</span>
        </div>

        <div class="row-2">
            <div class="field">
                <label for="phone">Phone</label>
                <div class="input-wrap">
                    <span class="input-icon">📞</span>
                    <input type="tel" id="phone" name="phone" placeholder="+91 98765 43210">
                </div>
                <span class="field-hint">Optional</span>
            </div>
            <div class="field">
                <label for="salary">Salary (₹)</label>
                <div class="input-wrap">
                    <span class="input-icon">₹</span>
                    <input type="number" id="salary" name="salary" placeholder="50000" step="0.01" min="1" required>
                </div>
                <span class="field-error" id="salary-err">Must be positive.</span>
            </div>
        </div>

        <button type="submit" class="btn">Register Employee →</button>
    </form>
    <div class="card-footer">Fields marked <span>required</span> must be filled.</div>
</div>
<script>
    const form = document.getElementById('regForm');
    function setValid(el, ok) { el.classList.toggle('valid', ok); el.classList.toggle('invalid', !ok); }
    function showErr(id, show) { document.getElementById(id).classList.toggle('show', show); }
    function validateField(el) {
        const id = el.id;
        if (id==='id') { const v=parseInt(el.value); const ok=!isNaN(v)&&v>=1&&v<=20; setValid(el,ok); showErr('id-err',!ok&&el.value!==''); }
        else if (id==='department') { const ok=el.value!==''; setValid(el,ok); showErr('dept-err',!ok); }
        else if (id==='name') { const ok=el.value.trim().length>0; setValid(el,ok); showErr('name-err',!ok&&el.value!==''); }
        else if (id==='email') { const ok=/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(el.value); setValid(el,ok); showErr('email-err',!ok&&el.value!==''); }
        else if (id==='salary') { const v=parseFloat(el.value); const ok=!isNaN(v)&&v>0; setValid(el,ok); showErr('salary-err',!ok&&el.value!==''); }
    }
    form.querySelectorAll('input,select').forEach(el => {
        el.addEventListener('blur', ()=>validateField(el));
        el.addEventListener('input', ()=>validateField(el));
    });
    form.addEventListener('submit', e => {
        let valid=true;
        ['id','department','name','email','salary'].forEach(f => {
            const el=document.getElementById(f);
            validateField(el);
            if (el.classList.contains('invalid')||el.value==='') valid=false;
        });
        if (!valid) e.preventDefault();
    });
</script>
</body>
</html>
